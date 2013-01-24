package com.artclod.sickdays.simulation.conf

import scalaz.Lens

case class LocationConf(name: String, numberEmployees: Int, employeeConstitution: Int) {

}

object LocationConf {
	val numberEmployeesLens = Lens[LocationConf, Int](_.numberEmployees, (obj, field) => obj.copy(numberEmployees = field))

	val employeeConstitutionLens = Lens[LocationConf, Int](_.employeeConstitution, (obj, field) => obj.copy(employeeConstitution = field))
}