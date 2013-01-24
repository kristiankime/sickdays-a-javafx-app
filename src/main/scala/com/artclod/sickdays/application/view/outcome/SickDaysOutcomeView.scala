package com.artclod.sickdays.application.view.outcome

import org.tbee.javafx.scene.layout.MigPane

import com.artclod.javafx.sugar.ObservableSugar.bean2Fluent
import com.artclod.javafx.sugar.ObservableSugar.ifExistsElse
import com.artclod.sickdays.application.presentation.outcome.SickDaysOutcomePresentation

import javafx.beans.binding.DoubleBinding
import javafx.beans.binding.ObjectBinding
import javafx.beans.binding.StringBinding
import javafx.beans.property.Property
import javafx.collections.FXCollections
import javafx.scene.chart.BarChart
import javafx.scene.chart.CategoryAxis
import javafx.scene.chart.NumberAxis
import javafx.scene.chart.XYChart
import javafx.scene.control.Label

class SickDaysOutcomeView(presentation: SickDaysOutcomePresentation) {
	private val sickDaysMean = new Label().w(_.textProperty.bind(new ToStringBinding(presentation.daysSickMean)))
	private val workDaysMean = new Label().w(_.textProperty.bind(new ToStringBinding(presentation.daysWorkingMean)))

	val xAxis = new CategoryAxis
	val yAxis = new NumberAxis
	val chart = new BarChart(xAxis, yAxis)
	chart.setTitle("Outcome")
	xAxis.setLabel("Sick / Working")
	yAxis.setLabel("Days")
	chart.getData().add(createSeries(presentation))

	val surface = new MigPane("fill, wrap 2")
	surface.add(new Label("Sick"))
	surface.add(sickDaysMean)
	surface.add(new Label("Work"))
	surface.add(workDaysMean)
	surface.add(chart, "span 2")

	private def createSeries(presentation: SickDaysOutcomePresentation) = {
		val series1Value1 = new XYChart.Data[String, Number]("Sick", 0)
		series1Value1.YValueProperty().bind(new NonEmptyDoubleBinding(0d)(presentation.daysSickMean))
		val series1Value2 = new XYChart.Data[String, Number]("Work", 0)
		series1Value2.YValueProperty().bind(new NonEmptyDoubleBinding(0d)(presentation.daysWorkingMean))

		new XYChart.Series[String, Number]().w(_.getData().addAll(series1Value1, series1Value2))
	}
}

class ToStringBinding(p: Property[_]) extends StringBinding {
	bind(p)
	override def computeValue = { ifExistsElse(p.getValue()) { _.toString() } { "" } }
	override def getDependencies = { FXCollections.singletonObservableList(p) }
	override def dispose = { unbind(p) }
}

class NonEmptyDoubleBinding(default: Double)(p: Property[_ <: Number]) extends DoubleBinding {
	bind(p)
	override def computeValue = { ifExistsElse(p.getValue()) { _.doubleValue() } { default } }
	override def getDependencies = { FXCollections.singletonObservableList(p) }
	override def dispose = { unbind(p) }
}

class NonEmptyBinding[T](default: T)(p: Property[_ <: T]) extends ObjectBinding[T] {
	bind(p)
	override def computeValue = { ifExistsElse(p.getValue()) { (t: T) => t } { default } }
	override def getDependencies = { FXCollections.singletonObservableList(p) }
	override def dispose = { unbind(p) }
}