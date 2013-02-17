package com.artclod.sickdays.application.presentation.outcome

import com.artclod.javafx.swap.beans.BeanSwap
import com.artclod.javafx.sugar.ObservableSugar.function2BeanGetter
import com.artclod.sickdays.application.model.outcome.SickDaysOutcomeModel
import com.artclod.javafx.swap.beans.BeanRef

class SickDaysOutcomePresentation(employee: BeanRef[SickDaysOutcomeModel]) {
	val daysSickMean = employee.getProperty((m : SickDaysOutcomeModel) => m.daysSickMean)
	val daysSickVariance = employee.getProperty((m : SickDaysOutcomeModel) => m.daysSickVariance)
	val daysWorkingMean = employee.getProperty((m : SickDaysOutcomeModel) => m.daysWorkingMean)
	val daysWorkingVariance = employee.getProperty((m : SickDaysOutcomeModel) => m.daysWorkingVariance)
}