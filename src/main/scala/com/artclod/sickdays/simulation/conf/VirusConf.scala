package com.artclod.sickdays.simulation.conf

import scalaz.Lens

case class VirusConf(virulence: Int)

object VirusConf {
	val virulenceL: Lens[VirusConf, Int] = Lens.lensu((o, v) => o.copy(virulence = v), _.virulence)
}