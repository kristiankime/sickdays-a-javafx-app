package com.artclod.sickdays.simulation.conf

import com.artclod.sickdays.simulation.conf.VirusConf._
import scalaz.Lens

case class SickDaysConf(randomSeed: Long, duration: Int, locations: Array[LocationConf], virus: VirusConf)

// for help on Lenses see http://www.monadzoo.com/blog/2012/11/18/using-lenses-with-scalaz-7/
object SickDaysConf {
	val randomSeedL: Lens[SickDaysConf, Long] = Lens.lensu((o, v) => o.copy(randomSeed = v), _.randomSeed)
	val durationL: Lens[SickDaysConf, Int] = Lens.lensu((o, v) => o.copy(duration = v), _.duration)
	val locationsL: Lens[SickDaysConf, Array[LocationConf]] = Lens.lensu((o, v) => o.copy(locations = v), _.locations)
	val virusL: Lens[SickDaysConf, VirusConf] = Lens.lensu((o, v) => o.copy(virus = v), _.virus)

	val virusVirulanceL = virusL >=> virulenceL
}