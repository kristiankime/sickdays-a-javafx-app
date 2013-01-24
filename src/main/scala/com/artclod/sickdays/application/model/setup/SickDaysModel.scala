package com.artclod.sickdays.application.model.setup

import scala.collection.JavaConversions.seqAsJavaList

import com.artclod.javafx.sugar.PropertyFactory.prop

import javafx.collections.FXCollections.observableArrayList

class SickDaysModel(durationValue: Int, virusValue: VirusModel, locationsValue: LocationModel*) {
	val duration = prop(durationValue)
	val virus = prop(virusValue)
	val locations = observableArrayList(seqAsJavaList(locationsValue))

	def newLocation = locations.add(new LocationModel())
	def removeLocation(l: LocationModel) = { locations.remove(l) }
}