package com.artclod.sickdays.application.model.setup

import com.artclod.javafx.sugar.PropertyFactory.prop

class LocationObs(data: LocationData = new LocationData) {
	val name = prop(data.name)
	val numberEmployees = prop(data.numberEmployees)
	val employeeConstitution = prop(data.employeeConstitution)

	def toData = LocationData(name.getValue, numberEmployees.getValue, employeeConstitution.getValue)
}

case class LocationData(name: String = "Location", numberEmployees: Int = 10, employeeConstitution: Int = 10) {
	def toObs = new LocationObs(this)
}
