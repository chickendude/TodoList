package ch.ralena.todolist.di.components

import ch.ralena.todolist.di.modules.ActivityModule
import ch.ralena.todolist.di.modules.ApplicationModule
import ch.ralena.todolist.di.scopes.AppScope
import dagger.Component

@AppScope
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
	fun newActivityComponent(activityModule: ActivityModule): ActivityComponent
}