package com.artclod.sickdays.application.presentation.setup

import com.artclod.javafx.indirect.beans.IndirectBean
import com.artclod.javafx.sugar.ObservableSugar.function2BeanGetter
import com.artclod.sickdays.application.model.setup.VirusModel

class VirusPresentation(private val virus: IndirectBean[VirusModel]) {
	val virulance = virus.getIndirectProperty((m: VirusModel) => m.virulence);
}
