package com.artclod.sickdays.application.presentation

import com.artclod.javafx.indirect.beans.IndirectBean
import com.artclod.javafx.indirect.beans.UncontrolledIndirectBean
import com.artclod.javafx.indirect.collections.IndirectObservableListAndSelectionDelegate
import com.artclod.javafx.sugar.ObservableSugar.function2BeanGetter
import com.artclod.javafx.sugar.ObservableSugar.makeEventHandler
import com.artclod.sickdays.application.model.SickDaysScenariosModel

import javafx.event.ActionEvent
import javafx.event.EventHandler

class SickDaysScenariosPresentation(val sickDaysScenarios: IndirectBean[SickDaysScenariosModel]) {
	private val scenariosIndirect = sickDaysScenarios.getIndirectList((m: SickDaysScenariosModel) => m.scenarios)
	val scenarios = new IndirectObservableListAndSelectionDelegate(scenariosIndirect)
	scenarios.selectionModel().selectFirst()
	private val selectedScenarioBean = new UncontrolledIndirectBean(scenarios.selectionModel().selectedItemProperty());
	val selectedScenario = new SickDaysScenarioPresentation(selectedScenarioBean)

	val newScenarios : EventHandler[ActionEvent] = (a : Any) => sickDaysScenarios.setBean(new SickDaysScenariosModel)
	val add : EventHandler[ActionEvent] = (a : Any) => sickDaysScenarios.getBean().newScenario
	val remove : EventHandler[ActionEvent] = (a : Any) => sickDaysScenarios.getBean().removeScenario(scenarios.selectionModel().getSelectedItem())
}

object SickDaysScenariosPresentation {
	def apply(m: SickDaysScenariosModel) = new SickDaysScenariosPresentation(new IndirectBean(m))
}