package com.artclod.spray

import spray.json._
import scala.math.BigDecimal

object SpraySugar {

	def valueOrJsNull[T, R](x: T)(f: (T) => R) = { if (x == null) { JsNull } else { f(x) } }

	implicit def map2JsObject(m: Map[String, JsValue]) = new JsObject(m)

	implicit def bigDecimal2Int(n: BigDecimal) = n.toInt
	implicit def bigDecimal2Long(n: BigDecimal) = n.toLong
	implicit def bigDecimal2Float(n: BigDecimal) = n.toFloat
	implicit def bigDecimal2Double(n: BigDecimal) = n.toDouble
	
	def main(args: Array[String]) {
		System.err.println(JsNumber(null.asInstanceOf[BigDecimal]));
	}
}