package com.artclod.sickdays.runner.translation

import com.artclod.javafx.sugar.ObservableSugar.getValue2Value
import com.artclod.javafx.sugar.ObservableSugar.getValueNumber2Int
import com.artclod.sickdays.application.model.setup.LocationObs
import com.artclod.sickdays.simulation.conf.LocationConf

object LocationModel2LocationConf extends Function1[LocationObs, LocationConf] {
	def apply(model : LocationObs) = LocationConf(model.name, model.numberEmployees, model.employeeConstitution)
}