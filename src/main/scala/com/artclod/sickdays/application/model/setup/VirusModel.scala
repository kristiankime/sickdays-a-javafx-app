package com.artclod.sickdays.application.model.setup

import com.artclod.javafx.sugar.PropertyFactory.prop

class VirusModel(virulenceValue: Int = 10) {
	val virulence = prop(virulenceValue)
}
