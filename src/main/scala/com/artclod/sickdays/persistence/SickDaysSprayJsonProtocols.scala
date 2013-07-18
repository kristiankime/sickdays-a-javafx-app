package com.artclod.sickdays.persistence

import scala.collection.JavaConversions.asScalaBuffer
import com.artclod.javafx.sugar.ObservableSugar.getValue2Option
import com.artclod.javafx.sugar.ObservableSugar.getValue2Value
import com.artclod.javafx.sugar.ObservableSugar.getValueNumber2BigDecimal
import com.artclod.javafx.sugar.ObservableSugar.someOrNull
import com.artclod.sickdays.application.model.SickDaysScenarioObs
import com.artclod.sickdays.application.model.SickDaysScenariosObs
import com.artclod.sickdays.application.model.outcome.SickDaysOutcomeObs
import com.artclod.sickdays.application.model.setup.LocationObs
import com.artclod.sickdays.application.model.setup.SickDaysObs
import com.artclod.sickdays.application.model.setup.VirusObs
import com.artclod.spray.SpraySugar.bigDecimal2Double
import com.artclod.spray.SpraySugar.bigDecimal2Int
import com.artclod.spray.SpraySugar.map2JsObject
import spray.json.DefaultJsonProtocol
import spray.json.DeserializationException
import spray.json.JsArray
import spray.json.JsNumber
import spray.json.JsObject
import spray.json.JsString
import spray.json.JsValue
import spray.json.RootJsonFormat
import com.artclod.sickdays.application.model.setup.LocationData
import com.artclod.sickdays.application.model.setup.SickDaysData
import com.artclod.sickdays.application.model.setup.SickDaysData
import com.artclod.sickdays.application.model.setup.VirusData
import com.artclod.sickdays.application.model.SickDaysScenarioData
import com.artclod.sickdays.application.model.SickDaysScenariosData
import com.artclod.sickdays.application.model.outcome.SickDaysOutcomeData

object SickDaysSprayJsonProtocols extends DefaultJsonProtocol {
	private val locationModelFormat = lazyFormat(LocationModelJsonFormat)
	private val virusModelFormat = lazyFormat(VirusModelJsonFormat)
	private val sickDaysModelFormat = lazyFormat(SickDaysModelJsonFormat)
	private val sickDaysOutcomeModelFormat = lazyFormat(SickDaysOutcomeModelJsonFormat)
	private val sickDaysScenarioModelFormat = lazyFormat(SickDaysScenarioModelJsonFormat)

	implicit object SickDaysScenariosModelJsonFormat extends RootJsonFormat[SickDaysScenariosObs] {
		def write(v: SickDaysScenariosObs) = {
			JsObject("scenarios" -> JsArray(List(v.scenarios.map((m: SickDaysScenarioObs) => sickDaysScenarioModelFormat.write(m)): _*)))
		}

		def read(value: JsValue) = {
			value.asJsObject.getFields("scenarios") match {
				case Seq(JsArray(scenarios)) => SickDaysScenariosData( scenarios.map(sickDaysScenarioModelFormat.read(_).toData).iterator.toIterable ).toObs
				case _ => throw new DeserializationException(this.getClass().getSimpleName() + " was unable to parse " + value.prettyPrint)
			}
		}
	}

	implicit object SickDaysScenarioModelJsonFormat extends RootJsonFormat[SickDaysScenarioObs] {
		def write(o: SickDaysScenarioObs) = JsObject("name" -> JsString(o.name), "setup" -> sickDaysModelFormat.write(o.setup), "outcome" -> sickDaysOutcomeModelFormat.write(o.outcome))

		def read(value: JsValue) = {
			value.asJsObject.getFields("name", "setup", "outcome") match {
				case Seq(JsString(name), JsObject(setup), JsObject(outcome)) => SickDaysScenarioData(name, sickDaysModelFormat.read(setup).toData, someOrNull(sickDaysOutcomeModelFormat.read(outcome))).toObs
				case _ => throw new DeserializationException(this.getClass().getSimpleName() + " was unable to parse " + value.prettyPrint)
			}
		}
	}

	implicit object SickDaysModelJsonFormat extends RootJsonFormat[SickDaysObs] {
		def write(o: SickDaysObs) = JsObject("duration" -> JsNumber(o.duration), "virus" -> virusModelFormat.write(o.virus), "locations" -> JsArray(List(o.locations.map((m : LocationObs) => locationModelFormat.write(m)): _* )))

		def read(value: JsValue) = {
			value.asJsObject.getFields("duration", "virus", "locations") match {
				case Seq(JsNumber(duration), JsObject(virus), JsArray(locations)) => SickDaysData(duration, virusModelFormat.read(virus).toData, locations.map(locationModelFormat.read(_)).map(_.toData) : _* ).toObs
				case _ => throw new DeserializationException(this.getClass().getSimpleName() + " was unable to parse " + value.prettyPrint)
			}
		}
	}

	implicit object LocationModelJsonFormat extends RootJsonFormat[LocationObs] {
		def write(v: LocationObs) = JsObject("name" -> JsString(v.name), "numberEmployees" -> JsNumber(v.numberEmployees), "employeeConstitution" -> JsNumber(v.employeeConstitution))

		def read(value: JsValue) = {
			value.asJsObject.getFields("name", "numberEmployees", "employeeConstitution") match {
				case Seq(JsString(name),  JsNumber(numberEmployees), JsNumber(employeeConstitution)) => new LocationObs(LocationData(name = name, numberEmployees = numberEmployees, employeeConstitution = employeeConstitution))
				case _ => throw new DeserializationException(LocationModelJsonFormat.this.getClass().getSimpleName() + " was unable to parse " + value.prettyPrint)
			}
		}
	}

	implicit object VirusModelJsonFormat extends RootJsonFormat[VirusObs] {
		def write(v: VirusObs) = JsObject("virulence" -> JsNumber(v.virulence))

		def read(value: JsValue) = {
			value.asJsObject.getFields("virulence") match {
				case Seq(JsNumber(virulence)) => VirusData(virulence).toObs
				case _ => throw new DeserializationException(this.getClass().getSimpleName() + " was unable to parse " + value.prettyPrint)
			}
		}
	}

	implicit object SickDaysOutcomeModelJsonFormat extends RootJsonFormat[Option[SickDaysOutcomeObs]] {
		def write(o: Option[SickDaysOutcomeObs]) = o match {
			case Some(v) => JsObject("daysSickMean" -> JsNumber(v.daysSickMean), "daysSickVariance" -> JsNumber(v.daysSickVariance), "daysWorkingMean" -> JsNumber(v.daysWorkingMean), "daysWorkingVariance" -> JsNumber(v.daysWorkingVariance))
			case None => JsObject()
		}

		def read(value: JsValue) = {
			value.asJsObject.getFields("daysSickMean", "daysSickVariance", "daysWorkingMean", "daysWorkingVariance") match {
				case Seq(JsNumber(daysSickMean), JsNumber(daysSickVariance), JsNumber(daysWorkingMean), JsNumber(daysWorkingVariance)) => Option(SickDaysOutcomeData(daysSickMean, daysSickVariance, daysWorkingMean, daysWorkingVariance).toObs)
				case Seq() => None
				case _ => throw new DeserializationException(this.getClass().getSimpleName() + " was unable to parse " + value.prettyPrint)
			}
		}
	}
}