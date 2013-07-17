package com.artclod.sickdays.application.model.setup

import scala.collection.JavaConversions._
import com.artclod.javafx.sugar.PropertyFactory._

import javafx.collections.FXCollections.observableArrayList

class SickDaysObs(data: SickDaysData) {
	val duration = prop(data.duration)
	val virus = prop(data.virus.toObs)
	val locations = obs(data.locations.map(_.toObs))

	def newLocation = locations.add(LocationData().toObs)
	def removeLocation(l: LocationObs) = { locations.remove(l) }

	def toData = new SickDaysData(duration.getValue, virus.getValue.toData, locations.toSeq.map(_.toData): _*)
}

case class SickDaysData(duration: Int, virus: VirusData, locations: LocationData*) {
	def toObs = new SickDaysObs(this)
}
