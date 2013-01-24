package com.artclod.sickdays.application.view.setup

import com.artclod.javafx.Releasable
import com.artclod.javafx.scene.control.PropertyTextFields.IntegerPropertyField
import com.artclod.javafx.sugar.ObservableSugar.bean2Fluent
import com.artclod.sickdays.application.presentation.setup.LocationPresentation

import javafx.geometry.Insets
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.HBox

class LocationView(presenter: LocationPresentation) extends HBox with Releasable {
	this.paddingProperty().setValue(new Insets(0d))
	private val name = new TextField().w(_.textProperty.bindBidirectional(presenter.name))
	private val number = new IntegerPropertyField().w(_.valueProperty.bindBidirectional(presenter.numberEmployees))
	private val constitution = new IntegerPropertyField().w(_.valueProperty.bindBidirectional(presenter.employeeConstitution))
	private val remove = new Button("Remove").w(_.setOnAction(presenter.removeLocation))
	
	getChildren().add(new Label("Name"));
	getChildren().add(name);
	getChildren().add(new Label("Number"));
	getChildren().add(number);
	getChildren().add(new Label("Durablity"));
	getChildren().add(constitution);
	getChildren().add(remove);
	
	def release { /* LATER do we need to release all the bindings or can we rely on the presentation model to release? */ }
}