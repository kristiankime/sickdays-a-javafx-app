package com.artclod.sickdays.runner

import org.apache.commons.math.stat.descriptive.DescriptiveStatistics

import com.artclod.sickdays.application.model.outcome.SickDaysOutcomeModel
import com.artclod.sickdays.simulation.SickDaysSim
import com.artclod.sickdays.simulation.conf.SickDaysConf
import com.artclod.sickdays.simulation.result.SickDaysResult

class SickDaysScenarioRunner {
	def run(confs: Seq[SickDaysConf]) =
		confs.map(new SickDaysSim).foldLeft(new ResultsDescriptive)(new ResultFolder).toSickDaysOutcomeModel
}

private class ResultFolder extends Function2[ResultsDescriptive, SickDaysResult, ResultsDescriptive] {
	def apply(resultsDescriptive: ResultsDescriptive, sickDaysResult: SickDaysResult) = { resultsDescriptive + sickDaysResult }
}

private class ResultsDescriptive() {
	val daysSick = new DescriptiveStatistics()
	val daysWorking = new DescriptiveStatistics()

	def +(r: SickDaysResult) = {
		daysSick.addValue(r.daysSick)
		daysWorking.addValue(r.daysWorking)
		this
	}

	def toSickDaysOutcomeModel = new SickDaysOutcomeModel(daysSick.getMean, daysSick.getVariance, daysWorking.getMean, daysWorking.getVariance)
}
