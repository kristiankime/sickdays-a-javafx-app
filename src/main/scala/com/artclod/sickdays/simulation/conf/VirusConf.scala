package com.artclod.sickdays.simulation.conf

import scalaz.Lens


case class VirusConf(virulence: Int) {

}

object VirusConf {
	
	val virulenceLens = Lens[VirusConf, Int](_.virulence, (obj, field) => obj.copy(virulence = field))

}