package com.artclod.sickdays.application.view.setup

import org.tbee.javafx.scene.layout.MigPane

import com.artclod.javafx.scene.control.PropertyTextFields.IntegerPropertyField
import com.artclod.javafx.sugar.ObservableSugar.bean2Fluent
import com.artclod.sickdays.application.presentation.setup.VirusPresentation

import javafx.scene.control.Label

class VirusView(presenter: VirusPresentation) {
	val virulance = new IntegerPropertyField().w(_.valueProperty().bindBidirectional(presenter.virulance))
		
	val surface = new MigPane("fill, wrap 2, inset 0 0")
	surface.add(new Label("Virulance"))
	surface.add(virulance);
}