package com.artclod.sickdays.simulation

import scala.Array.canBuildFrom

import com.artclod.sickdays.simulation.conf.LocationConf
import com.artclod.sickdays.simulation.conf.SickDaysConf
import com.artclod.sickdays.simulation.result.LocationResult
import com.artclod.sickdays.simulation.result.SickDaysResult

class SickDaysSim extends Function1[SickDaysConf, SickDaysResult] {

	def apply(c: conf.SickDaysConf) = {
		val virulanceJiggle = (((c.randomSeed * 7) % 11) - 6) / 100d
		val jiggledVirulance = Array(0, c.virus.virulence + virulanceJiggle).max

		val locationResults = c.locations.map((l: LocationConf) => {
			val diseasePenetration = jiggledVirulance / (l.employeeConstitution + jiggledVirulance).toDouble
			val manDays = c.duration * l.numberEmployees
			val daysSick = (manDays * diseasePenetration).toInt
			val daysWorking = manDays - daysSick
			new LocationResult(daysSick, daysWorking)
		})

		val totalDaysSick = locationResults.map(_.daysSick).sum
		val totaldaysWorking = locationResults.map(_.daysWorking).sum

		SickDaysResult(totalDaysSick, totaldaysWorking, locationResults)
	}
}