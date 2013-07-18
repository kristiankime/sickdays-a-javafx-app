package com.artclod.sickdays.application.model.outcome

import com.artclod.javafx.sugar.PropertyFactory.prop

class SickDaysOutcomeObs(data: SickDaysOutcomeData) {
	val daysSickMean = prop(data.sickMean)
	val daysSickVariance = prop(data.sickVariance)
	val daysWorkingMean = prop(data.workingMean)
	val daysWorkingVariance = prop(data.workingVariance)

	def toData = SickDaysOutcomeData(daysSickMean.getValue, daysSickVariance.getValue, daysWorkingMean.getValue, daysWorkingVariance.getValue)
}

case class SickDaysOutcomeData(sickMean: Double, sickVariance: Double, workingMean: Double, workingVariance: Double) {
	def toObs = new SickDaysOutcomeObs(this)
}