package com.artclod.sickdays.application.view

import java.io.IOException

import org.parboiled.errors.ParsingException

import com.artclod.javafx.dialog.Dialog
import com.artclod.javafx.sugar.ObservableSugar.bean2Fluent
import com.artclod.javafx.sugar.ObservableSugar.makeEventHandler
import com.artclod.javafx.sugar.ObservableSugar.writeToFile
import com.artclod.sickdays.application.model.SickDaysScenariosModel
import com.artclod.sickdays.application.presentation.SickDaysScenariosPresentation
import com.artclod.sickdays.persistence.SickDaysSprayJsonProtocols.SickDaysScenariosModelJsonFormat

import javafx.scene.control.Menu
import javafx.scene.control.MenuBar
import javafx.scene.control.MenuItem
import javafx.stage.FileChooser
import javafx.stage.Stage
import spray.json.pimpAny
import spray.json.pimpString

// LATER is this useful / why doesn't it work?
//				val d = new File(getClass().getResource(".").getFile())
//				System.err.println(d);
//				fileChooser.setInitialDirectory(d)

// LATER this causes a Core Dump wtf?
//				val extFilter = new FileChooser.ExtensionFilter("Sick Days files (*.sck)", "*.sck")
//				fileChooser.getExtensionFilters().add(extFilter)
object SickDaysApplicationMenu {

	def menuBar(stage: Stage, presenter: SickDaysScenariosPresentation) = {
		val fileMenu = new Menu("File")
		fileMenu.getItems().add(newMenuItem(stage, presenter))
		fileMenu.getItems().add(saveMenuItem(stage, presenter))
		fileMenu.getItems().add(loadMenuItem(stage, presenter))

		val menuBar = new MenuBar()
		menuBar.getMenus().add(fileMenu)
		menuBar
	}

	private def newMenuItem(stage: Stage, presenter: SickDaysScenariosPresentation): javafx.scene.control.MenuItem = {
		new MenuItem("New").w(_.setOnAction((e : Any) => newScenarios(stage, presenter)))
	}

	private def newScenarios(stage: Stage, presenter: SickDaysScenariosPresentation): Unit = {
		new Dialog.Builder().create().setOwner(null).setTitle("New Scenario").setInfoIcon() //
			.setMessage("Creating a new set of scenarios will not save any changes") //
			.addConfirmationButton("Continue (without saving changes)", presenter.newScenarios).addCancelButton(null).build().show()
	}

	private def saveMenuItem(stage: Stage, presenter: SickDaysScenariosPresentation): javafx.scene.control.MenuItem = {
		new MenuItem("Save").w(_.setOnAction((e : Any) => saveFile(stage, presenter)))
	}

	private def saveFile(stage: Stage, presenter: SickDaysScenariosPresentation): Unit = {
		val bean = presenter.sickDaysScenarios.getBean()
		if (bean == null) {
			throw new IllegalStateException("scenarios bean was null, system should always have a scenarios bean")
		}

		val fileChooser = new FileChooser()
		val file = fileChooser.showSaveDialog(stage)
		if (file != null) {
			try {
				writeToFile(file, bean.toJson.prettyPrint)
			} catch {
				case ioe: IOException => new Dialog.Builder().create().setOwner(null).setTitle("Save File Error").setErrorIcon().setStackTrace(ioe) //
					.setMessage("Error saving file was unable to write to (" + file.getAbsolutePath() + ")") //
					.addConfirmationButton("Try again", (e : Any) => saveFile(stage, presenter)).addCancelButton(null).build().show()
				case e: Exception => throw e
			}
		}
	}

	private def loadMenuItem(stage: Stage, presenter: SickDaysScenariosPresentation): MenuItem = {
		new MenuItem("Load").w(_.setOnAction((e : Any) => loadFile(stage, presenter)))
	}

	private def loadFile(stage: Stage, presenter: SickDaysScenariosPresentation): Unit = {
		val fileChooser = new FileChooser()
		val file = fileChooser.showOpenDialog(stage)
		if (file != null) {
			try {
				val fileText = io.Source.fromFile(file).mkString
				val model = fileText.asJson.convertTo[SickDaysScenariosModel]
				presenter.sickDaysScenarios.setBean(model)
			} catch {
				case pe: ParsingException => new Dialog.Builder().create().setOwner(null).setTitle("Load File Error").setErrorIcon().setStackTrace(pe) //
					.setMessage("Error loding file was unable to parse (" + file.getAbsolutePath() + ")") //
					.addConfirmationButton("Try again", (e : Any) => loadFile(stage, presenter)).addCancelButton(null).build().show()
				case ioe: IOException => new Dialog.Builder().create().setOwner(null).setTitle("Load File Error").setErrorIcon().setStackTrace(ioe) //
					.setMessage("Error loding file was unable to read (" + file.getAbsolutePath() + ")") //
					.addConfirmationButton("Try again", (e : Any) => loadFile(stage, presenter)).addCancelButton(null).build().show()
				case e: Exception => throw e
			}
		}
	}
}