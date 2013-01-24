package com.artclod.sickdays.runner.translation

import com.artclod.sickdays.application.model.setup.VirusModel
import com.artclod.sickdays.simulation.conf.VirusConf

object VirusModel2VirusConf extends Function1[VirusModel, VirusConf] {
	def apply(model : VirusModel) = new VirusConf(model.virulence.getValue())
}