package com.artclod.sickdays.application.view

import org.tbee.javafx.scene.layout.MigPane

import com.artclod.javafx.sugar.ObservableSugar.bean2Fluent
import com.artclod.sickdays.application.presentation.SickDaysScenarioPresentation
import com.artclod.sickdays.application.view.setup.SickDaysView

import javafx.scene.control.Button
import javafx.scene.control.TextField

class SickDaysScenarioView(presenter: SickDaysScenarioPresentation) {
	private val name = new TextField().w(_.setMinHeight(25d)).w(_.textProperty().bindBidirectional(presenter.name))
	private val sickDays = new SickDaysView(presenter.sickDays)
	private val run = new Button("Run").w(_.setOnAction(presenter.run))
	
	val surface = new MigPane("fill, wrap 1, inset 0 0")
	surface.add(name)
	surface.add(sickDays.surface, "grow")
	surface.add(run)
}