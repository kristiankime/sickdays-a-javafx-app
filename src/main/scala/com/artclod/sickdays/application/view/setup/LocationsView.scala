package com.artclod.sickdays.application.view.setup

import com.artclod.javafx.collections.ObservableListMirror
import com.artclod.javafx.sugar.ObservableSugar.function2MirrorFactoryForReleaseable
import com.artclod.sickdays.application.presentation.setup.LocationPresentation

import javafx.beans.binding.Bindings
import javafx.collections.ObservableList
import javafx.scene.Node
import javafx.scene.layout.VBox

class LocationsView(presenters: ObservableList[LocationPresentation]) {
	private val locations = new ObservableListMirror((p: LocationPresentation) => new LocationView(p), presenters)
	 
	val surface = new VBox
	Bindings.bindContentBidirectional(surface.getChildren(), locations.asInstanceOf[ObservableList[Node]])
}