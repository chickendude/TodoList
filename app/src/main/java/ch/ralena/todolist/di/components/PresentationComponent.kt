package ch.ralena.todolist.di.components

import ch.ralena.todolist.di.modules.PresentationModule
import ch.ralena.todolist.di.PresentationScope
import ch.ralena.todolist.ui.activities.MainActivity
import ch.ralena.todolist.ui.fragments.NewTodoListFragment
import dagger.Subcomponent

@PresentationScope
@Subcomponent(modules = [PresentationModule::class])
interface PresentationComponent {
	// activities
	fun inject(activity: MainActivity)

	// fragments
	fun inject(fragment: NewTodoListFragment)
}