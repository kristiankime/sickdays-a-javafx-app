package com.artclod.sickdays.application.presentation.setup

import com.artclod.javafx.collections.ObservableListMirror
import com.artclod.javafx.indirect.beans.IndirectBean
import com.artclod.javafx.sugar.ObservableSugar.function2BeanGetter
import com.artclod.javafx.sugar.ObservableSugar.function2MirrorFactoryForReleaseable
import com.artclod.javafx.sugar.ObservableSugar.ifExists
import com.artclod.javafx.sugar.ObservableSugar.makeEventHandler
import com.artclod.sickdays.application.model.setup.LocationModel
import com.artclod.sickdays.application.model.setup.SickDaysModel

import javafx.event.ActionEvent
import javafx.event.EventHandler

class SickDaysPresentation(val sickDays: IndirectBean[SickDaysModel]) {
	val duration = sickDays.getIndirectProperty((m: SickDaysModel) => m.duration)
	val virus = new VirusPresentation(sickDays.getIndirectPropertyBean((m: SickDaysModel) => m.virus))
	val locations = new ObservableListMirror((m: LocationModel) => new LocationPresentation(new IndirectBean(m), this), //
			sickDays.getIndirectList((m: SickDaysModel) => m.locations))
	
	val newLocation : EventHandler[ActionEvent] = (e : ActionEvent) => ifExists(sickDays.getBean()){_.newLocation}
}