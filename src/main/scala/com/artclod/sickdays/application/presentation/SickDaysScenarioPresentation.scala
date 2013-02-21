package com.artclod.sickdays.application.presentation

import com.artclod.javafx.sugar.ObservableSugar.function2BeanGetter
import com.artclod.javafx.sugar.ObservableSugar.ifExists
import com.artclod.javafx.sugar.ObservableSugar.makeEventHandler
import com.artclod.javafx.swap.beans.BeanRef
import com.artclod.sickdays.application.model.SickDaysScenarioModel
import com.artclod.sickdays.application.presentation.outcome.SickDaysOutcomePresentation
import com.artclod.sickdays.application.presentation.setup.SickDaysPresentation

import javafx.event.ActionEvent

class SickDaysScenarioPresentation(val sickDaysScenario: BeanRef[SickDaysScenarioModel]) {
	val name = sickDaysScenario.createPropertyRef((m: SickDaysScenarioModel) => m.name)
	val sickDays = new SickDaysPresentation(sickDaysScenario.createBeanRefFromProperty((m: SickDaysScenarioModel) => m.setup))
	val outcome = new SickDaysOutcomePresentation(sickDaysScenario.createBeanRefFromProperty((m: SickDaysScenarioModel) => m.outcome))
		
	val run = makeEventHandler((event: ActionEvent) => ifExists(sickDaysScenario.getBean) { _.run() })
}