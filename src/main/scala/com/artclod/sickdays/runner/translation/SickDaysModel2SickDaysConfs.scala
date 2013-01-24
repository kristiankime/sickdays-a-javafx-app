package com.artclod.sickdays.runner.translation

import scala.Array.canBuildFrom
import scala.collection.JavaConversions.asScalaBuffer

import com.artclod.javafx.sugar.ObservableSugar.getValue2Value
import com.artclod.sickdays.application.model.setup.SickDaysModel
import com.artclod.sickdays.simulation.conf.SickDaysConf


object SickDaysModel2SickDaysConfs extends Function1[SickDaysModel, IndexedSeq[SickDaysConf]] {
	def apply(model : SickDaysModel) : IndexedSeq[SickDaysConf] = {
		val locations = model.locations.toIterator.toArray.map(LocationModel2LocationConf)
		val virus = VirusModel2VirusConf(model.virus)
		
		for(seed <- 1 to 10) yield { new SickDaysConf(seed, model.duration.getValue(), locations, virus) }
	}
}