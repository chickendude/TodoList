package ch.ralena.todolist.di.modules

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(
		val application: Application
) {

	@Provides
	fun application() = application
}