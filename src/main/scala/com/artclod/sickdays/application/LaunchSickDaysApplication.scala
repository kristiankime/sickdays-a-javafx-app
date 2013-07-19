package com.artclod.sickdays.application

import com.artclod.sickdays.application.model.SickDaysScenariosObs
import com.artclod.sickdays.application.presentation.SickDaysScenariosPresentation
import com.artclod.sickdays.application.view.SickDaysApplicationView
import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage
import com.artclod.sickdays.application.model.SickDaysScenariosData

class LaunchSickDaysApplication extends Application {

	override def start(stage: Stage) {
		val applicationView = createApplication(stage)
		val scene = new Scene(applicationView, 800, 600)
		stage.setScene(scene)
		stage.show()		
	}
		
	private def createApplication(stage: Stage) = {
		// Model
		val sickDaysScenariosModel = SickDaysScenariosData().toObs
//		sickDaysScenariosModel.newScenario

		// Presentation
		val sickDaysScenariosPresentation = SickDaysScenariosPresentation(sickDaysScenariosModel)

		// View
		val sickDaysApplicationView = new SickDaysApplicationView(stage, sickDaysScenariosPresentation)
		sickDaysApplicationView.surface
	}

}

object LaunchSickDaysApplication {

	def main(args: Array[String]) {
		Application.launch(classOf[LaunchSickDaysApplication], args: _*)
	}
	
}