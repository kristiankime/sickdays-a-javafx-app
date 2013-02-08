package com.artclod.sickdays.application.presentation.setup

import com.artclod.javafx.swap.beans.BeanSwap
import com.artclod.javafx.sugar.ObservableSugar.function2BeanGetter
import com.artclod.sickdays.application.model.setup.VirusModel

class VirusPresentation(private val virus: BeanSwap[VirusModel]) {
	val virulance = virus.getProperty((m: VirusModel) => m.virulence);
}
