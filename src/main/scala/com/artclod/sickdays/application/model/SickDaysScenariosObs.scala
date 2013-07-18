package com.artclod.sickdays.application.model

import scala.collection.JavaConversions._
import scala.collection.Iterable
import scala.collection.immutable.HashSet
import com.artclod.javafx.collections.BeanObservableList
import com.artclod.javafx.sugar.ObservableSugar.function2BeanGetter
import com.artclod.sickdays.application.model.setup.LocationObs
import com.artclod.sickdays.application.model.setup.SickDaysObs
import com.artclod.sickdays.application.model.setup.VirusObs
import com.artclod.sickdays.application.model.setup.SickDaysData
import com.artclod.sickdays.application.model.setup.VirusData
import com.artclod.sickdays.application.model.setup.VirusData
import com.artclod.sickdays.application.model.setup.LocationData

class SickDaysScenariosObs(data : SickDaysScenariosData) {
	val scenarios = BeanObservableList.create(data.initialScenarios.map(_.toObs), (m:SickDaysScenarioObs) => m.name);
	
	def newScenario() = scenarios.add( SickDaysScenarioData(setup = SickDaysData(10, VirusData(), LocationData()) ).toObs )
	def removeScenario(m :SickDaysScenarioObs) = scenarios.remove(m)

	def toData = SickDaysScenariosData(scenarios.toSeq.map(_.toData))
}

case class SickDaysScenariosData(initialScenarios : Iterable[SickDaysScenarioData] = new HashSet) {
	def toObs = new SickDaysScenariosObs(this)
}
