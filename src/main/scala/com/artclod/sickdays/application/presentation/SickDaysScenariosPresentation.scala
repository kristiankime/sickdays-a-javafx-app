package com.artclod.sickdays.application.presentation

import com.artclod.javafx.sugar.ObservableSugar.function2BeanGetter
import com.artclod.javafx.sugar.ObservableSugar.makeEventHandler
import com.artclod.javafx.swap.beans.BeanRef
import com.artclod.javafx.swap.beans.BeanSwap
import com.artclod.javafx.swap.beans.impl.SimpleBeanRef
import com.artclod.javafx.swap.beans.impl.SimpleBeanSwap
import com.artclod.javafx.swap.collections.impl.ArrayObservableListAndSelectionSwap
import com.artclod.sickdays.application.model.SickDaysScenariosObs
import javafx.event.ActionEvent
import javafx.event.EventHandler
import com.artclod.sickdays.application.model.SickDaysScenariosData

class SickDaysScenariosPresentation(val sickDaysScenarios: BeanSwap[SickDaysScenariosObs]) {
	val scenarios = new ArrayObservableListAndSelectionSwap(sickDaysScenarios.createListRef((m: SickDaysScenariosObs) => m.scenarios))
	scenarios.selectionModel().selectFirst()
	private val selectedScenarioBean = new SimpleBeanRef(scenarios.selectionModel().selectedItemProperty());
	val selectedScenario = new SickDaysScenarioPresentation(selectedScenarioBean)

	val newScenarios : EventHandler[ActionEvent] = (a : Any) => sickDaysScenarios.swapRefObject( SickDaysScenariosData().toObs)
	val add : EventHandler[ActionEvent] = (a : Any) => sickDaysScenarios.getBean.newScenario
	val remove : EventHandler[ActionEvent] = (a : Any) => sickDaysScenarios.getBean.removeScenario(scenarios.selectionModel().getSelectedItem())
}

object SickDaysScenariosPresentation {
	def apply(m: SickDaysScenariosObs) = new SickDaysScenariosPresentation(new SimpleBeanSwap(m))
}