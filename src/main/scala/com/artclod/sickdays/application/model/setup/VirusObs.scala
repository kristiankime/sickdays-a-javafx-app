package com.artclod.sickdays.application.model.setup

import com.artclod.javafx.sugar.PropertyFactory.prop

class VirusObs(data: VirusData) {
	val virulence = prop(data.virulence)

	def toData = VirusData(virulence.getValue)
}

case class VirusData(virulence: Int = 10) {
	def toObs = new VirusObs(this)
}