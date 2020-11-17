package ch.ralena.todolist.ui

import androidx.fragment.app.FragmentManager
import ch.ralena.todolist.R
import ch.ralena.todolist.ui.fragments.MainFragment
import ch.ralena.todolist.ui.fragments.NewTodoListFragment
import ch.ralena.todolist.ui.fragments.TodoListFragment
import javax.inject.Inject

class ScreensNavigator @Inject constructor(
		private val fragmentManager: FragmentManager
) {
	companion object {
		const val NEW_TODO_LIST_TAG = "newtodolist"
	}

	fun toMainFragment() {
		fragmentManager
				.beginTransaction()
				.add(R.id.placeHolder, MainFragment())
				.commit()
	}

	fun toTodoListFragment() {
		fragmentManager
				.beginTransaction()
				.add(R.id.placeHolder, TodoListFragment())
				.commit()
	}

	fun toNewTodoListFragment() {
		fragmentManager
				.beginTransaction()
				.addToBackStack(NEW_TODO_LIST_TAG)
				.add(R.id.placeHolder, NewTodoListFragment())
				.commit()
	}
}
