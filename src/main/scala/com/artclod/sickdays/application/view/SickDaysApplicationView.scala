package com.artclod.sickdays.application.view

import com.artclod.sickdays.application.presentation.SickDaysScenariosPresentation
import com.artclod.sickdays.application.view.outcome.SickDaysOutcomeView

import javafx.scene.layout.BorderPane
import javafx.stage.Stage

class SickDaysApplicationView(stage: Stage, presenter: SickDaysScenariosPresentation) {
	private val menu = SickDaysApplicationMenu.menuBar(stage, presenter)
	private val scenarios = new SickDaysScenariosView(presenter)
	private val selectedOutcome = new SickDaysOutcomeView(presenter.selectedScenario.outcome)

	val surface = new BorderPane()
	surface.topProperty().setValue(menu)
	surface.leftProperty().setValue(scenarios.surface)
	surface.centerProperty().setValue(selectedOutcome.surface)
}