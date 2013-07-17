package com.artclod.javafx.sugar

import scala.collection.JavaConversions._

import javafx.beans.property.BooleanProperty
import javafx.beans.property.DoubleProperty
import javafx.beans.property.FloatProperty
import javafx.beans.property.IntegerProperty
import javafx.beans.property.LongProperty
import javafx.beans.property.Property
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleFloatProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleLongProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import javafx.collections.FXCollections

object PropertyFactory {
	def prop[T](bean : Any, name : String, t : T) : Property[T] = new SimpleObjectProperty(bean, name, t)

	def prop(bean : Any, name : String, t : Boolean) : BooleanProperty = new SimpleBooleanProperty(bean, name, t)
	
	def prop(bean : Any, name : String, t : Int) : IntegerProperty = new SimpleIntegerProperty(bean, name, t)

	def prop(bean : Any, name : String, t : Long) : LongProperty = new SimpleLongProperty(bean, name, t)

	def prop(bean : Any, name : String, t : Double) : DoubleProperty = new SimpleDoubleProperty(bean, name, t)

	def prop(bean : Any, name : String, t : Float) : FloatProperty = new SimpleFloatProperty(bean, name, t)
	
	def prop(bean : Any, name : String, t : String) : StringProperty = new SimpleStringProperty(bean, name, t)
	
	def prop[T](t : T) : Property[T] = new SimpleObjectProperty(t)

	def prop(t : Boolean) : BooleanProperty = new SimpleBooleanProperty(t)
	
	def prop(t : Int) : IntegerProperty = new SimpleIntegerProperty(t)

	def prop(t : Long) : LongProperty = new SimpleLongProperty(t)

	def prop(t : Double) : DoubleProperty = new SimpleDoubleProperty(t)

	def prop(t : Float) : FloatProperty = new SimpleFloatProperty(t)
	
	def prop(t : String) : StringProperty = new SimpleStringProperty(t)
	
	def obs[T](t : Seq[T]) = FXCollections.observableList(seqAsJavaList(t))
}