package com.artclod.sickdays.persistence

import scala.collection.mutable.ArrayBuffer
import com.artclod.sickdays.application.model.setup.VirusModel
import com.artclod.sickdays.application.model.SickDaysScenarioModel
import com.artclod.sickdays.application.model.SickDaysScenariosModel
import com.artclod.sickdays.persistence.SickDaysSprayJsonProtocols.SickDaysScenarioModelJsonFormat
import com.artclod.sickdays.persistence.SickDaysSprayJsonProtocols.SickDaysScenariosModelJsonFormat
import spray.json.pimpAny
import com.artclod.sickdays.application.model.setup.SickDaysModel
import com.artclod.sickdays.application.model.setup.LocationObs
import com.artclod.sickdays.application.model.setup.LocationData

object SickDaysSprayJsonProtocolsTest {
	def main(args: Array[String]) {
	  val vM = new SickDaysScenariosModel(ArrayBuffer(
	  		new SickDaysScenarioModel("one", new SickDaysModel(10, new VirusModel(10), LocationData("L1").toObs, LocationData("L2").toObs), null), 
	  		new SickDaysScenarioModel("two", new SickDaysModel(10, new VirusModel(10), new LocationObs()), null)
	  		))
	  val vMAST = vM.toJson
	  System.err.println(vMAST)
	  System.err.println(vMAST.prettyPrint)
	  System.err.println(vMAST.convertTo[SickDaysScenariosModel])
	}
  
  private def sickDaysScenario: Unit = {
	  val vM = new SickDaysScenarioModel("one", new SickDaysModel(10, new VirusModel(10)), null)
	  val vMAST = vM.toJson
	  System.err.println(vMAST)
	  System.err.println(vMAST.prettyPrint)
	  System.err.println(vMAST.convertTo[SickDaysScenarioModel])
	}
}