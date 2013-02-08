package com.artclod.javafx.sugar

import java.io.File
import java.io.FileWriter

import scala.math.BigDecimal

import com.artclod.javafx.Releasable
import com.artclod.javafx.collections.ObservableListMirror
import com.artclod.javafx.swap.beans.getter.Getter

import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.event.ActionEvent
import javafx.event.EventHandler

object ObservableSugar {
	def using[A <: { def close(): Unit }, B](param: A)(f: A => B): B = try { f(param) } finally { param.close() }

	def writeToFile(file: File, data: String) = using(new FileWriter(file)) { fileWriter => fileWriter.write(data) }

	def someOrNull[T](t: Option[T]): T = t match {
		case Some(v) => v
		case None => null.asInstanceOf[T]
	}

	def ifExists[T, R](t: T)(f: T => R): Option[R] = { if (t != null && t != None) { Some(f(t)) } else { None } }

	def ifExistsElse[T, R](t: T)(f: T => R)(r: R): R = { if (t != null && t != None) { f(t) } else { r } }

	implicit def getValue2Value[T](o: { def getValue(): T }): T = { o.getValue }
	implicit def getValueNumber2BigDecimal(o: { def getValue(): Number }) = { BigDecimal(o.getValue.doubleValue) }
	implicit def getValueNumber2Int(o: { def getValue(): Number }) = { o.getValue.intValue }
	implicit def getValueNumber2Long(o: { def getValue(): Number }) = { o.getValue.longValue }
	implicit def getValueNumber2Float(o: { def getValue(): Number }) = { o.getValue.floatValue }
	implicit def getValueNumber2Double(o: { def getValue(): Number }) = { o.getValue.doubleValue }
	implicit def getValue2Option[T](o: { def getValue(): T }): Option[T] = {
		if (o.getValue == null || o.getValue == None) None
		else Option(o.getValue)
	}
	implicit def getValueNumber2BigDecimalOption(o: { def getValue(): Number }): Option[BigDecimal] = {
		val n = o.getValue;
		if (n == null) None
		else Option(BigDecimal(n.doubleValue()))
	}

	implicit def bean2Fluent[B](b: B) = new FluentBean[B](b)
	final class FluentBean[B](val b: B) {
		def w[V](f: (B) => Unit) = { f(b); b }
	}

	implicit def function2BeanGetter[T, B](f: B => T): BeanGetter[T, B] = new BeanGetter[T, B](f)
	final class BeanGetter[T, B](val f: B => T) extends Getter[T, B] {
		def get(b: B) = f(b)
	}

	implicit def function2MirrorFactoryForReleaseable[M <: Releasable, O](f: O => M): MirrorFactoryForReleaseable[M, O] = new MirrorFactoryForReleaseable[M, O](f)
	final class MirrorFactoryForReleaseable[M <: Releasable, O](f: O => M) extends ObservableListMirror.MirrorFactory[M, O] {
		def create(o: O) = f(o)
		def finished(m: M) = m.release
	}

	implicit def makeEventHandler[E <: ActionEvent](action: (E) => Any) = new EventHandler[E] { override def handle(event: E) { action(event) } }
	
	implicit def makeChangeListener1[T](action: () => Unit) = new ChangeListener[T] {
		override def changed(obsV: ObservableValue[_ <: T], oldV: T, newV: T): Unit = { action() }
	}
	implicit def makeChangeListener2[T](action: (T) => Unit) = new ChangeListener[T] {
		override def changed(obsV: ObservableValue[_ <: T], oldV: T, newV: T): Unit = { action(newV) }
	}
	implicit def makeChangeListener3[T](action: (T, T) => Unit) = new ChangeListener[T] {
		override def changed(obsV: ObservableValue[_ <: T], oldV: T, newV: T): Unit = { action(oldV, newV) }
	}
	implicit def makeChangeListener4[T](action: (ObservableValue[_ <: T], T, T) => Unit) = new ChangeListener[T] {
		override def changed(obsV: ObservableValue[_ <: T], oldV: T, newV: T): Unit = { action(obsV, oldV, newV) }
	}
}