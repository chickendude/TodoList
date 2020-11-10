package ch.ralena.todolist.di.modules

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(
		private val application: Application
) {

	@Provides
	fun application() = application
}