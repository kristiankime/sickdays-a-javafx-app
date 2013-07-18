package com.artclod.sickdays.runner

import org.apache.commons.math.stat.descriptive.DescriptiveStatistics
import com.artclod.sickdays.application.model.outcome.SickDaysOutcomeObs
import com.artclod.sickdays.simulation.SickDaysSim
import com.artclod.sickdays.simulation.conf.SickDaysConf
import com.artclod.sickdays.simulation.result.SickDaysResult
import com.artclod.sickdays.application.model.outcome.SickDaysOutcomeData

class SickDaysScenarioRunner {
	def run(confs: Seq[SickDaysConf]) =
		confs.map(new SickDaysSim).foldLeft(new ResultsDescriptive)(new ResultFolder).toSickDaysOutcomeModel
}

private class ResultFolder extends ((ResultsDescriptive, SickDaysResult) => ResultsDescriptive) {
	def apply(resultsDescriptive: ResultsDescriptive, sickDaysResult: SickDaysResult) = { resultsDescriptive + sickDaysResult }
}

private case class ResultsDescriptive(daysSick : DescriptiveStatistics = new DescriptiveStatistics(), daysWorking : DescriptiveStatistics = new DescriptiveStatistics()) {

	def +(r: SickDaysResult) = {
		daysSick.addValue(r.daysSick)
		daysWorking.addValue(r.daysWorking)
		this
	}

	def toSickDaysOutcomeModel = SickDaysOutcomeData(daysSick.getMean, daysSick.getVariance, daysWorking.getMean, daysWorking.getVariance).toObs
}
