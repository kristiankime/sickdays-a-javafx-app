package com.artclod.sickdays.application.model.outcome

import com.artclod.javafx.sugar.PropertyFactory.prop

class SickDaysOutcomeModel(sickMean: Double, sickVariance: Double, workingMean: Double, workingVariance: Double) {
	val daysSickMean = prop(sickMean)
	val daysSickVariance = prop(sickVariance)
	val daysWorkingMean = prop(workingMean)
	val daysWorkingVariance = prop(workingVariance)
}