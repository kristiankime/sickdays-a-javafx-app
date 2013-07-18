package com.artclod.sickdays.persistence

import scala.collection.mutable.ArrayBuffer
import com.artclod.sickdays.application.model.setup.VirusObs
import com.artclod.sickdays.application.model.SickDaysScenarioObs
import com.artclod.sickdays.application.model.SickDaysScenariosObs
import com.artclod.sickdays.persistence.SickDaysSprayJsonProtocols.SickDaysScenarioModelJsonFormat
import com.artclod.sickdays.persistence.SickDaysSprayJsonProtocols.SickDaysScenariosModelJsonFormat
import spray.json.pimpAny
import com.artclod.sickdays.application.model.setup.SickDaysObs
import com.artclod.sickdays.application.model.setup.LocationObs
import com.artclod.sickdays.application.model.setup.LocationData
import com.artclod.sickdays.application.model.setup.SickDaysData
import com.artclod.sickdays.application.model.setup.SickDaysData
import com.artclod.sickdays.application.model.setup.VirusData
import com.artclod.sickdays.application.model.SickDaysScenarioData
import com.artclod.sickdays.application.model.SickDaysScenariosData

object SickDaysSprayJsonProtocolsTest {
	def main(args: Array[String]) {
	  val vM = SickDaysScenariosData(ArrayBuffer(
	  		SickDaysScenarioData("one", SickDaysData(10, VirusData(10), Seq(LocationData("L1"), LocationData("L2"))), null), 
	  		SickDaysScenarioData("two", SickDaysData(10, VirusData(10), Seq(LocationData())), null)
	  		)).toObs
	  val vMAST = vM.toJson
	  System.err.println(vMAST)
	  System.err.println(vMAST.prettyPrint)
	  System.err.println(vMAST.convertTo[SickDaysScenariosObs])
	}
  
  private def sickDaysScenario: Unit = {
	  val vM = SickDaysScenarioData("one", SickDaysData(10, VirusData(10)), null).toObs
	  val vMAST = vM.toJson
	  System.err.println(vMAST)
	  System.err.println(vMAST.prettyPrint)
	  System.err.println(vMAST.convertTo[SickDaysScenarioObs])
	}
}