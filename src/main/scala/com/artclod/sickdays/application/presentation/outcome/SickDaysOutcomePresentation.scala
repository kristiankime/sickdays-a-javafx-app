package com.artclod.sickdays.application.presentation.outcome

import com.artclod.javafx.indirect.beans.IndirectBean
import com.artclod.javafx.sugar.ObservableSugar.function2BeanGetter
import com.artclod.sickdays.application.model.outcome.SickDaysOutcomeModel

class SickDaysOutcomePresentation(employee: IndirectBean[SickDaysOutcomeModel]) {
	val daysSickMean = employee.getIndirectProperty((m : SickDaysOutcomeModel) => m.daysSickMean)
	val daysSickVariance = employee.getIndirectProperty((m : SickDaysOutcomeModel) => m.daysSickVariance)
	val daysWorkingMean = employee.getIndirectProperty((m : SickDaysOutcomeModel) => m.daysWorkingMean)
	val daysWorkingVariance = employee.getIndirectProperty((m : SickDaysOutcomeModel) => m.daysWorkingVariance)
}