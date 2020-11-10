package ch.ralena.todolist

import android.app.Application
import ch.ralena.todolist.di.components.ApplicationComponent
import ch.ralena.todolist.di.components.DaggerApplicationComponent
import ch.ralena.todolist.di.modules.ApplicationModule

class MyApplication: Application() {
	val applicationComponent = DaggerApplicationComponent
			.builder()
			.applicationModule(ApplicationModule(this))
			.build()
}