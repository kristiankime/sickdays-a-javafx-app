package com.artclod.sickdays.application.view

import org.tbee.javafx.scene.layout.MigPane

import com.artclod.javafx.sugar.ObservableSugar.bean2Fluent
import com.artclod.sickdays.application.presentation.SickDaysScenariosPresentation

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.ListView
import javafx.scene.control.ScrollPane
import javafx.scene.layout.BorderPane

class SickDaysScenariosView(presenter: SickDaysScenariosPresentation) {
	private val scenarioList = new ListView(presenter.scenarios).w(_.setSelectionModel(presenter.scenarios.selectionModel))
	private val contactListScrollPane = new ScrollPane().w(_.setContent(scenarioList)).w(_.fitToHeightProperty().setValue(true)).w(_.fitToWidthProperty().setValue(true))
	private val add = new Button("add").w(_.setOnAction(presenter.add))
	private val remove = new Button("remove").w(_.setOnAction(presenter.remove))
	
	private val selectedScenario = new SickDaysScenarioView(presenter.selectedScenario)

	val surface = new MigPane("fill, wrap 1", "[]", "[300px][grow]")
	surface.add(scenariosPane, "grow")
	surface.add(selectedScenario.surface, "grow")

	def scenariosPane() = {
		val scenariosPane = new BorderPane
		scenariosPane.topProperty().setValue(new Label("Scenarios"))
		scenariosPane.centerProperty().setValue(contactListScrollPane)
		val scenariosControls = new MigPane("fill, insets 0 0")
		scenariosControls.add(add, "center")
		scenariosControls.add(remove, "center")
		scenariosPane.bottomProperty().setValue(scenariosControls)
		scenariosPane
	}
}

