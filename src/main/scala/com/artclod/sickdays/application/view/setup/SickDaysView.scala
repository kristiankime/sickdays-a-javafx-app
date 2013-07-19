package com.artclod.sickdays.application.view.setup

import org.tbee.javafx.scene.layout.MigPane

import com.artclod.javafx.scene.control.PropertyTextFields.IntegerPropertyField
import com.artclod.javafx.sugar.ObservableSugar.bean2Fluent
import com.artclod.sickdays.application.presentation.setup.SickDaysPresentation

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.ScrollPane

class SickDaysView(presenter: SickDaysPresentation) {
	private val duration = new IntegerPropertyField().w(_.valueProperty.bindBidirectional(presenter.duration))
	private val virus = new VirusView(presenter.virus)
	private val locations = new LocationsView(presenter.locations)

	val surface = new MigPane("fill, wrap 2, insets 0 0")
	surface.add(new Label("Duration"))
	surface.add(duration)
	surface.add(virus.surface, "span 2")
	surface.add(new Label("Locations"), "span 2")
	surface.add(new Button("New").w(_.setOnAction(presenter.newLocation)), "span 2")
	surface.add(new ScrollPane().w(_.setContent(locations.surface)) , "grow, span 2")
}