package ch.ralena.todolist.di.modules

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ch.ralena.todolist.di.ActivityScope
import ch.ralena.todolist.ui.home.todolists.TodoListItemAdapter
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
	fun todoListItemAdapter(): TodoListItemAdapter = TodoListItemAdapter(arrayListOf(), activity)

	@Provides
	@ActivityScope
	fun fragmentManager() = activity.supportFragmentManager
}