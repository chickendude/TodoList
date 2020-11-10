package ch.ralena.todolist.di.components

import ch.ralena.todolist.di.modules.ActivityModule
import ch.ralena.todolist.di.modules.PresentationModule
import ch.ralena.todolist.di.scopes.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
	fun newPresentationComponent(presentationModule: PresentationModule): PresentationComponent
}