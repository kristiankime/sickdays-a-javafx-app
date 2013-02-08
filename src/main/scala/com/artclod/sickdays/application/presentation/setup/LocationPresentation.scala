package com.artclod.sickdays.application.presentation.setup

import com.artclod.javafx.Releasable
import com.artclod.javafx.swap.beans.BeanSwap
import com.artclod.javafx.sugar.ObservableSugar.function2BeanGetter
import com.artclod.javafx.sugar.ObservableSugar.ifExists
import com.artclod.javafx.sugar.ObservableSugar.makeEventHandler
import com.artclod.sickdays.application.model.setup.LocationModel

import javafx.event.ActionEvent
import javafx.event.EventHandler

class LocationPresentation(val location: BeanSwap[LocationModel], val sickDays: SickDaysPresentation) extends Releasable {
	val name = location.getProperty((m: LocationModel) => m.name);
	val numberEmployees = location.getProperty((m: LocationModel) => m.numberEmployees);
	val employeeConstitution = location.getProperty((m: LocationModel) => m.employeeConstitution);
	
	def release { location.release() }
	
	val removeLocation : EventHandler[ActionEvent] = (e : ActionEvent) => ifExists(sickDays.sickDays.getBean()){_.removeLocation(location.getBean())}
}
