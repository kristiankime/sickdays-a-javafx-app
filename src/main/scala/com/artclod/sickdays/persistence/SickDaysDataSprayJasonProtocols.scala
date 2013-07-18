package com.artclod.sickdays.persistence

import spray.json._
import com.artclod.sickdays.application.model.setup._
import com.artclod.sickdays.application.model.outcome._
import com.artclod.sickdays.application.model._

object SickDaysDataSprayJasonProtocols extends DefaultJsonProtocol {
	implicit val locationFormat = jsonFormat3(LocationData)
	implicit val virusFormat = jsonFormat1(VirusData)
	implicit val sickDaysFormat = jsonFormat3(SickDaysData)
	implicit val sickDaysOutcomeFormat = jsonFormat4(SickDaysOutcomeData)
	implicit val sickDaysScenarioFormat = jsonFormat3(SickDaysScenarioData)
	implicit val sickDaysScenarisoFormat = jsonFormat1(SickDaysScenariosData)
}
