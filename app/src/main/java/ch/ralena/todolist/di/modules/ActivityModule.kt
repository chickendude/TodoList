package ch.ralena.todolist.di.modules

import android.app.Activity
import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import ch.ralena.todolist.di.scopes.ActivityScope
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
}