package com.artclod.sickdays.persistence

import scala.collection.JavaConversions.asScalaBuffer

import com.artclod.javafx.sugar.ObservableSugar.getValue2Option
import com.artclod.javafx.sugar.ObservableSugar.getValue2Value
import com.artclod.javafx.sugar.ObservableSugar.getValueNumber2BigDecimal
import com.artclod.javafx.sugar.ObservableSugar.someOrNull
import com.artclod.sickdays.application.model.SickDaysScenarioModel
import com.artclod.sickdays.application.model.SickDaysScenariosModel
import com.artclod.sickdays.application.model.outcome.SickDaysOutcomeModel
import com.artclod.sickdays.application.model.setup.LocationModel
import com.artclod.sickdays.application.model.setup.SickDaysModel
import com.artclod.sickdays.application.model.setup.VirusModel
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

object SickDaysSprayJsonProtocols extends DefaultJsonProtocol {
	private val locationModelFormat = lazyFormat(LocationModelJsonFormat)
	private val virusModelFormat = lazyFormat(VirusModelJsonFormat)
	private val sickDaysModelFormat = lazyFormat(SickDaysModelJsonFormat)
	private val sickDaysOutcomeModelFormat = lazyFormat(SickDaysOutcomeModelJsonFormat)
	private val sickDaysScenarioModelFormat = lazyFormat(SickDaysScenarioModelJsonFormat)

	implicit object SickDaysScenariosModelJsonFormat extends RootJsonFormat[SickDaysScenariosModel] {
		def write(v: SickDaysScenariosModel) = {
			JsObject("scenarios" -> JsArray(List(v.scenarios.map((m: SickDaysScenarioModel) => sickDaysScenarioModelFormat.write(m)): _*)))
		}

		def read(value: JsValue) = {
			value.asJsObject.getFields("scenarios") match {
				case Seq(JsArray(scenarios)) => new SickDaysScenariosModel(initialScenarios = scenarios.map(sickDaysScenarioModelFormat.read(_)).iterator.toIterable)
				case _ => throw new DeserializationException(this.getClass().getSimpleName() + " was unable to parse " + value.prettyPrint)
			}
		}
	}

	implicit object SickDaysScenarioModelJsonFormat extends RootJsonFormat[SickDaysScenarioModel] {
		def write(o: SickDaysScenarioModel) = JsObject("name" -> JsString(o.name), "setup" -> sickDaysModelFormat.write(o.setup), "outcome" -> sickDaysOutcomeModelFormat.write(o.outcome))

		def read(value: JsValue) = {
			value.asJsObject.getFields("name", "setup", "outcome") match {
				case Seq(JsString(name), JsObject(setup), JsObject(outcome)) => new SickDaysScenarioModel(nameValue = name, setupValue = sickDaysModelFormat.read(setup), outcomeValue = someOrNull(sickDaysOutcomeModelFormat.read(outcome)))
				case _ => throw new DeserializationException(this.getClass().getSimpleName() + " was unable to parse " + value.prettyPrint)
			}
		}
	}

	implicit object SickDaysModelJsonFormat extends RootJsonFormat[SickDaysModel] {
		def write(o: SickDaysModel) = JsObject("duration" -> JsNumber(o.duration), "virus" -> virusModelFormat.write(o.virus), "locations" -> JsArray(List(o.locations.map((m : LocationModel) => locationModelFormat.write(m)): _* )))

		def read(value: JsValue) = {
			value.asJsObject.getFields("duration", "virus", "locations") match {
				case Seq(JsNumber(duration), JsObject(virus), JsArray(locations)) => new SickDaysModel(durationValue = duration, virusValue = virusModelFormat.read(virus), locationsValue = locations.map(locationModelFormat.read(_)) : _* )
				case _ => throw new DeserializationException(this.getClass().getSimpleName() + " was unable to parse " + value.prettyPrint)
			}
		}
	}

	implicit object LocationModelJsonFormat extends RootJsonFormat[LocationModel] {
		def write(v: LocationModel) = JsObject("name" -> JsString(v.name), "numberEmployees" -> JsNumber(v.numberEmployees), "employeeConstitution" -> JsNumber(v.employeeConstitution))

		def read(value: JsValue) = {
			value.asJsObject.getFields("name", "numberEmployees", "employeeConstitution") match {
				case Seq(JsString(name),  JsNumber(numberEmployees), JsNumber(employeeConstitution)) => new LocationModel(nameValue = name, numberValue = numberEmployees, constitutionValue = employeeConstitution)
				case _ => throw new DeserializationException(LocationModelJsonFormat.this.getClass().getSimpleName() + " was unable to parse " + value.prettyPrint)
			}
		}
	}

	implicit object VirusModelJsonFormat extends RootJsonFormat[VirusModel] {
		def write(v: VirusModel) = JsObject("virulence" -> JsNumber(v.virulence))

		def read(value: JsValue) = {
			value.asJsObject.getFields("virulence") match {
				case Seq(JsNumber(virulence)) => new VirusModel(virulenceValue = virulence)
				case _ => throw new DeserializationException(this.getClass().getSimpleName() + " was unable to parse " + value.prettyPrint)
			}
		}
	}

	implicit object SickDaysOutcomeModelJsonFormat extends RootJsonFormat[Option[SickDaysOutcomeModel]] {
		def write(o: Option[SickDaysOutcomeModel]) = o match {
			case Some(v) => JsObject("daysSickMean" -> JsNumber(v.daysSickMean), "daysSickVariance" -> JsNumber(v.daysSickVariance), "daysWorkingMean" -> JsNumber(v.daysWorkingMean), "daysWorkingVariance" -> JsNumber(v.daysWorkingVariance))
			case None => JsObject()
		}

		def read(value: JsValue) = {
			value.asJsObject.getFields("daysSickMean", "daysSickVariance", "daysWorkingMean", "daysWorkingVariance") match {
				case Seq(JsNumber(daysSickMean), JsNumber(daysSickVariance), JsNumber(daysWorkingMean), JsNumber(daysWorkingVariance)) => Option(new SickDaysOutcomeModel(sickMean = daysSickMean, sickVariance = daysSickVariance, workingMean = daysWorkingMean, workingVariance = daysWorkingVariance))
				case Seq() => None
				case _ => throw new DeserializationException(this.getClass().getSimpleName() + " was unable to parse " + value.prettyPrint)
			}
		}
	}
}