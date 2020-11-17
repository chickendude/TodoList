package ch.ralena.todolist.ui.base

import androidx.appcompat.app.AppCompatActivity
import ch.ralena.todolist.MyApplication
import ch.ralena.todolist.di.modules.ActivityModule
import ch.ralena.todolist.di.modules.PresentationModule

open class BaseActivity : AppCompatActivity() {
	private val applicationComponent get() = (application as MyApplication).applicationComponent

	val activityComponent by lazy {
		applicationComponent.newActivityComponent(
				ActivityModule(this)
		)
	}

	private val presentationComponent by lazy {
		activityComponent.newPresentationComponent(PresentationModule())
	}

	val injector get() = presentationComponent
}