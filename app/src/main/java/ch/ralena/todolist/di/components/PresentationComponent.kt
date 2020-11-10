package ch.ralena.todolist.di.components

import ch.ralena.todolist.di.modules.PresentationModule
import ch.ralena.todolist.di.scopes.PresentationScope
import ch.ralena.todolist.ui.activities.MainActivity
import dagger.Subcomponent

@PresentationScope
@Subcomponent(modules = [PresentationModule::class])
interface PresentationComponent {
	fun inject(activity: MainActivity)
}