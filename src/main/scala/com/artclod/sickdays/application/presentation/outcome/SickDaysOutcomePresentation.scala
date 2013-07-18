package com.artclod.sickdays.application.presentation.outcome

import com.artclod.javafx.swap.beans.BeanSwap
import com.artclod.javafx.sugar.ObservableSugar.function2BeanGetter
import com.artclod.sickdays.application.model.outcome.SickDaysOutcomeObs
import com.artclod.javafx.swap.beans.BeanRef

class SickDaysOutcomePresentation(employee: BeanRef[SickDaysOutcomeObs]) {
	val daysSickMean = employee.createPropertyRef((m : SickDaysOutcomeObs) => m.daysSickMean)
	val daysSickVariance = employee.createPropertyRef((m : SickDaysOutcomeObs) => m.daysSickVariance)
	val daysWorkingMean = employee.createPropertyRef((m : SickDaysOutcomeObs) => m.daysWorkingMean)
	val daysWorkingVariance = employee.createPropertyRef((m : SickDaysOutcomeObs) => m.daysWorkingVariance)
}