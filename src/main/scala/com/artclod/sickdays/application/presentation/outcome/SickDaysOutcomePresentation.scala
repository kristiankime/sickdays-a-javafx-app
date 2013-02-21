package com.artclod.sickdays.application.presentation.outcome

import com.artclod.javafx.swap.beans.BeanSwap
import com.artclod.javafx.sugar.ObservableSugar.function2BeanGetter
import com.artclod.sickdays.application.model.outcome.SickDaysOutcomeModel
import com.artclod.javafx.swap.beans.BeanRef

class SickDaysOutcomePresentation(employee: BeanRef[SickDaysOutcomeModel]) {
	val daysSickMean = employee.createPropertyRef((m : SickDaysOutcomeModel) => m.daysSickMean)
	val daysSickVariance = employee.createPropertyRef((m : SickDaysOutcomeModel) => m.daysSickVariance)
	val daysWorkingMean = employee.createPropertyRef((m : SickDaysOutcomeModel) => m.daysWorkingMean)
	val daysWorkingVariance = employee.createPropertyRef((m : SickDaysOutcomeModel) => m.daysWorkingVariance)
}