package com.artclod.sickdays.simulation.conf

import scalaz.Lens

case class LocationConf(name: String, numberEmployees: Int, employeeConstitution: Int)

object LocationConf {
	val numberEmployeesL: Lens[LocationConf, Int] = Lens.lensu((o, v) => o.copy(numberEmployees = v), _.numberEmployees)
	val employeeConstitutionL: Lens[LocationConf, Int] = Lens.lensu((o, v) => o.copy(employeeConstitution = v), _.employeeConstitution)
}