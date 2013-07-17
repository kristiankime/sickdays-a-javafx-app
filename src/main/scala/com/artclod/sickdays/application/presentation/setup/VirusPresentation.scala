package com.artclod.sickdays.application.presentation.setup

import com.artclod.javafx.swap.beans.BeanSwap
import com.artclod.javafx.sugar.ObservableSugar.function2BeanGetter
import com.artclod.sickdays.application.model.setup.VirusObs
import com.artclod.javafx.swap.beans.BeanRef

class VirusPresentation(private val virus: BeanRef[VirusObs]) {
	val virulance = virus.createPropertyRef((m: VirusObs) => m.virulence);
}
