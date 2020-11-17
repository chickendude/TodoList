package ch.ralena.todolist.di.modules

import androidx.appcompat.app.AppCompatActivity
import ch.ralena.todolist.di.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(
		private val activity: AppCompatActivity
) {

	@Provides
	fun activity() = activity

	@Provides
	@ActivityScope
	fun layoutInflater() = activity.layoutInflater

	@Provides
	@ActivityScope
	fun fragmentManager() = activity.supportFragmentManager
}