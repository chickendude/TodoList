package ch.ralena.todolist.di.components

import ch.ralena.todolist.di.ActivityScope
import ch.ralena.todolist.di.modules.ActivityModule
import ch.ralena.todolist.di.modules.FragmentModule
import ch.ralena.todolist.di.modules.PresentationModule
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
	fun newPresentationComponent(presentationModule: PresentationModule): PresentationComponent
	fun newFragmentComponent(fragmentModule: FragmentModule): FragmentComponent
}