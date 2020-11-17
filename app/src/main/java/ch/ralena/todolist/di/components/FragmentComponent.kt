package ch.ralena.todolist.di.components

import ch.ralena.todolist.di.FragmentScope
import ch.ralena.todolist.di.modules.FragmentModule
import ch.ralena.todolist.di.modules.PresentationModule
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [FragmentModule::class])
interface FragmentComponent {
	fun newPresentationComponent(presentationModule: PresentationModule): PresentationComponent
}