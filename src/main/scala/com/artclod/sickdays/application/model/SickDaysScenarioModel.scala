package com.artclod.sickdays.application.model

import com.artclod.javafx.sugar.PropertyFactory.prop
import com.artclod.sickdays.application.model.outcome.SickDaysOutcomeModel
import com.artclod.sickdays.application.model.setup.SickDaysModel
import com.artclod.sickdays.runner.SickDaysScenarioRunner
import com.artclod.sickdays.runner.translation.SickDaysModel2SickDaysConfs

import javafx.application.Platform
import javafx.concurrent.Service
import javafx.concurrent.Task


class SickDaysScenarioModel(nameValue : String = "sick days", setupValue : SickDaysModel, outcomeValue : SickDaysOutcomeModel = null ) {
	val name = prop(nameValue)
	val setup = prop(setupValue)
	val outcome = prop(outcomeValue)

	// LATER these are lazy for persistence only (Services can only be made on the JavaFX Application thread), is there a better way to do this?
	lazy private val scenarioRunnerService = new ScenarioRunnerService()
	lazy val progress = scenarioRunnerService.progressProperty();
	lazy val running = scenarioRunnerService.runningProperty()

	def run() = scenarioRunnerService.restart()

	override def toString = name.getValue()

	private class ScenarioRunnerService() extends Service[Unit] {
		override def createTask = {
			new Task[Unit]() {
				val sickDaysConfs = SickDaysModel2SickDaysConfs(setup.getValue())

				override def call() {
					val out = new SickDaysScenarioRunner().run(sickDaysConfs);
					Platform.runLater(new Runnable() { override def run { outcome.setValue(out) } })
				}
			}
		}
	}

}

