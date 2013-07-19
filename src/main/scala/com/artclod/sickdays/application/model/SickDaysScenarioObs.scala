package com.artclod.sickdays.application.model

import com.artclod.javafx.sugar.PropertyFactory.prop
import com.artclod.sickdays.application.model.outcome.SickDaysOutcomeObs
import com.artclod.sickdays.application.model.setup.SickDaysObs
import com.artclod.sickdays.runner.SickDaysScenarioRunner
import com.artclod.sickdays.runner.translation.SickDaysModel2SickDaysConfs
import javafx.application.Platform
import javafx.concurrent.Service
import javafx.concurrent.Task
import com.artclod.sickdays.application.model.setup.SickDaysData
import com.artclod.sickdays.application.model.outcome.SickDaysOutcomeData

class SickDaysScenarioObs(data: SickDaysScenarioData) {
	val name = prop(data.name)
	val setup = prop(data.setup.toObs)
	val outcome = prop(data.outcome.map(_.toObs).getOrElse(null))
		

	// LATER these are lazy for persistence only (Services can only be made on the JavaFX Application thread), is there a better way to do this?
	lazy private val scenarioRunnerService = new ScenarioRunnerService()
	lazy val progress = scenarioRunnerService.progressProperty();
	lazy val running = scenarioRunnerService.runningProperty()

	def run() = scenarioRunnerService.restart()

	override def toString = name.getValue()

	def toData = SickDaysScenarioData(name.getValue, setup.getValue.toData, Option(outcome.getValue.toData))

	private class ScenarioRunnerService() extends Service[Unit] {
		override def createTask = {
			new Task[Unit]() {
				val sickDaysConfs = SickDaysModel2SickDaysConfs(setup.getValue)

				override def call() {
					val out = new SickDaysScenarioRunner().run(sickDaysConfs);
					Platform.runLater(new Runnable() { override def run { outcome.setValue(out) } })
				}
			}
		}
	}

}

case class SickDaysScenarioData(name: String, setup: SickDaysData, outcome: Option[SickDaysOutcomeData] = None) {
	def toObs = new SickDaysScenarioObs(this)
}
