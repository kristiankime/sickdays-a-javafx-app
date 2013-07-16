package com.artclod.sickdays.application.presentation.setup

import com.artclod.javafx.Releasable
import com.artclod.javafx.swap.beans.BeanSwap
import com.artclod.javafx.sugar.ObservableSugar.function2BeanGetter
import com.artclod.javafx.sugar.ObservableSugar.ifExists
import com.artclod.javafx.sugar.ObservableSugar.makeEventHandler
import com.artclod.sickdays.application.model.setup.LocationObs

import javafx.event.ActionEvent
import javafx.event.EventHandler

class LocationPresentation(val location: BeanSwap[LocationObs], val sickDays: SickDaysPresentation) extends Releasable {
	val name = location.createPropertyRef((m: LocationObs) => m.name);
	val numberEmployees = location.createPropertyRef((m: LocationObs) => m.numberEmployees);
	val employeeConstitution = location.createPropertyRef((m: LocationObs) => m.employeeConstitution);
	
	def release { location.release() }
	
	val removeLocation : EventHandler[ActionEvent] = (e : ActionEvent) => ifExists(sickDays.sickDays.getBean){_.removeLocation(location.getBean)}
}
