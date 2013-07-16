package com.artclod.sickdays.simulation

import org.apache.commons.math.stat.descriptive.DescriptiveStatistics
import com.artclod.sickdays.simulation.conf.LocationConf
import com.artclod.sickdays.simulation.conf.SickDaysConf
import com.artclod.sickdays.simulation.conf.VirusConf
import com.artclod.sickdays.simulation.result.SickDaysResult
import com.artclod.sickdays.simulation.conf.LocationConf
import com.artclod.sickdays.simulation.conf.SickDaysConf._
import scalaz.LensPlus.LensPimp

object SickDaysMain {

	def main(args: Array[String]) {
		val sim = new SickDaysSim

		val baseConf = SickDaysConf(0L, 365, Array(LocationConf("location 1", 100, 10)), VirusConf(10))
		val configs = for (virulance <- 9 to 11; seed <- 1L to 3L) yield {

			// virusVirulanceL.mod( _ => virulance, randomSeedL.mod(_ => seed, baseConf))

			baseConf.mod(randomSeedL)(_ => seed).mod(virusVirulanceL)(_ => virulance)

		}

		val results = configs.map(c => (c, sim(c)))
		println(results.foldLeft("")((a: String, b: (SickDaysConf, SickDaysResult)) => a + "conf = " + b._1 + "\nresult = " + b._2 + "\n"))

		val resDesc = new ResultsDescriptive(results.unzip._2)
		println(resDesc);
	}

	private class ResultsDescriptive(results: Iterable[SickDaysResult]) {
		val daysSick = new DescriptiveStatistics()
		val daysWorking = new DescriptiveStatistics()
		results.foreach((r: SickDaysResult) => { daysSick.addValue(r.daysSick); daysWorking.addValue(r.daysWorking) })

		override def toString() = "daysSick = [" + daysSick + "]\ndaysWorking = [" + daysWorking + "]"
	}

}