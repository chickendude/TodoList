package ch.ralena.todolist.di.components

import ch.ralena.todolist.di.modules.ApplicationModule
import dagger.Component

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

}