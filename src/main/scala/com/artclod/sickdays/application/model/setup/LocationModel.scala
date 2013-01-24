package com.artclod.sickdays.application.model.setup

import com.artclod.javafx.sugar.PropertyFactory.prop

class LocationModel(nameValue : String = "Location", numberValue : Int = 10, constitutionValue : Int = 10) {
	val name = prop(nameValue)
	val numberEmployees = prop(numberValue)
	val employeeConstitution = prop(constitutionValue)
}