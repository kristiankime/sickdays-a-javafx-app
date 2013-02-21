package com.artclod.sickdays.application.presentation

import com.artclod.javafx.sugar.ObservableSugar.function2BeanGetter
import com.artclod.javafx.sugar.ObservableSugar.makeEventHandler
import com.artclod.javafx.swap.beans.BeanRef
import com.artclod.javafx.swap.beans.BeanSwap
import com.artclod.javafx.swap.beans.impl.SimpleBeanRef
import com.artclod.javafx.swap.beans.impl.SimpleBeanSwap
import com.artclod.javafx.swap.collections.impl.ArrayObservableListAndSelectionSwap
import com.artclod.sickdays.application.model.SickDaysScenariosModel

import javafx.event.ActionEvent
import javafx.event.EventHandler

class SickDaysScenariosPresentation(val sickDaysScenarios: BeanSwap[SickDaysScenariosModel]) {
	val scenarios = new ArrayObservableListAndSelectionSwap(sickDaysScenarios.createListRef((m: SickDaysScenariosModel) => m.scenarios))
	scenarios.selectionModel().selectFirst()
	private val selectedScenarioBean = new SimpleBeanRef(scenarios.selectionModel().selectedItemProperty());
	val selectedScenario = new SickDaysScenarioPresentation(selectedScenarioBean)

	val newScenarios : EventHandler[ActionEvent] = (a : Any) => sickDaysScenarios.swapRefObject(new SickDaysScenariosModel)
	val add : EventHandler[ActionEvent] = (a : Any) => sickDaysScenarios.getBean.newScenario
	val remove : EventHandler[ActionEvent] = (a : Any) => sickDaysScenarios.getBean.removeScenario(scenarios.selectionModel().getSelectedItem())
}

object SickDaysScenariosPresentation {
	def apply(m: SickDaysScenariosModel) = new SickDaysScenariosPresentation(new SimpleBeanSwap(m))
}