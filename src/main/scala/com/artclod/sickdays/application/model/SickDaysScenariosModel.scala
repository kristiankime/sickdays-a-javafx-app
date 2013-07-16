package com.artclod.sickdays.application.model

import scala.collection.Iterable
import scala.collection.JavaConversions.asJavaCollection
import scala.collection.immutable.HashSet

import com.artclod.javafx.collections.BeanObservableList
import com.artclod.javafx.sugar.ObservableSugar.function2BeanGetter
import com.artclod.sickdays.application.model.setup.LocationObs
import com.artclod.sickdays.application.model.setup.SickDaysModel
import com.artclod.sickdays.application.model.setup.VirusModel

class SickDaysScenariosModel(initialScenarios : Iterable[SickDaysScenarioModel] = new HashSet) {
	val scenarios = BeanObservableList.create(initialScenarios, (m:SickDaysScenarioModel) => m.name);
	
	def newScenario() = scenarios.add(new SickDaysScenarioModel(setupValue = new SickDaysModel(durationValue = 10, virusValue = new VirusModel, locationsValue = new LocationObs) ) )
	def removeScenario(m :SickDaysScenarioModel) = scenarios.remove(m)
}