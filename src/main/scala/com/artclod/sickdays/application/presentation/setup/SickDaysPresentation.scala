package com.artclod.sickdays.application.presentation.setup

import com.artclod.javafx.collections.ObservableListMirror
import com.artclod.javafx.swap.beans.BeanSwap
import com.artclod.javafx.sugar.ObservableSugar.function2BeanGetter
import com.artclod.javafx.sugar.ObservableSugar.function2MirrorFactoryForReleaseable
import com.artclod.javafx.sugar.ObservableSugar.ifExists
import com.artclod.javafx.sugar.ObservableSugar.makeEventHandler
import com.artclod.sickdays.application.model.setup.LocationObs
import com.artclod.sickdays.application.model.setup.SickDaysModel
import javafx.event.ActionEvent
import javafx.event.EventHandler
import com.artclod.javafx.swap.beans.BeanRef
import com.artclod.javafx.swap.beans.impl.SimpleBeanSwap

class SickDaysPresentation(val sickDays: BeanRef[SickDaysModel]) {
	val duration = sickDays.createPropertyRef((m: SickDaysModel) => m.duration)
	val virus = new VirusPresentation(sickDays.createBeanRefFromProperty((m: SickDaysModel) => m.virus))
	val locations = new ObservableListMirror((m: LocationObs) => new LocationPresentation(new SimpleBeanSwap(m), this), //
			sickDays.createListRef((m: SickDaysModel) => m.locations))
	
	val newLocation : EventHandler[ActionEvent] = (e : ActionEvent) => ifExists(sickDays.getBean){_.newLocation}
}