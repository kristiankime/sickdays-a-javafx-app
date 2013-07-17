package com.artclod.sickdays.runner.translation

import com.artclod.sickdays.application.model.setup.VirusObs
import com.artclod.sickdays.simulation.conf.VirusConf

object VirusModel2VirusConf extends Function1[VirusObs, VirusConf] {
	def apply(model : VirusObs) = new VirusConf(model.virulence.getValue())
}