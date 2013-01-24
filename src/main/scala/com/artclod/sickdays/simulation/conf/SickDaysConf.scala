package com.artclod.sickdays.simulation.conf

import scalaz.Lens
import scalaz.LensPlus.arrayLensTypeFix

case class SickDaysConf(randomSeed: Long, duration: Int, locations: Array[LocationConf], virus: VirusConf) {

}

object SickDaysConf {

	val randomSeedLens = Lens[SickDaysConf, Long](_.randomSeed, (obj, field) => obj.copy(randomSeed = field))
	// The above is the short version of this, which is probably easier to understand and included for clarity:
	//	val randomSeedLens = Lens(
	//		get = (_: SickDaysConf).randomSeed, 
	//		set = (obj: SickDaysConf, field: Long) => obj.copy(randomSeed = field))

	val durationLens = Lens[SickDaysConf, Int](_.duration, (obj, field) => obj.copy(duration = field))

	val locationsLens = Lens[SickDaysConf, Array[LocationConf]]( _.locations, (obj, field) => obj.copy(locations = field))

	def employeeDurabilityLens(index: Int) = locationsLens at index andThen LocationConf.employeeConstitutionLens

	def employeeNumberLens(index: Int) = locationsLens at index andThen LocationConf.numberEmployeesLens

	val virusLens = Lens[SickDaysConf, VirusConf](_.virus, (obj, field) => obj.copy(virus = field))
	
	val virusVirulanceLens = virusLens andThen VirusConf.virulenceLens
}