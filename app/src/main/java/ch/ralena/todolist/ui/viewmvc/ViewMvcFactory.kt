package ch.ralena.todolist.ui.viewmvc

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import ch.ralena.todolist.ui.activities.MainActivityViewMvc
import ch.ralena.todolist.ui.fragments.NewTodoListViewMvc
import ch.ralena.todolist.ui.home.HomeViewMvc
import ch.ralena.todolist.ui.home.todolists.TodoListItemAdapter
import javax.inject.Inject

open class ViewMvcFactory @Inject constructor(
		private val layoutInflater: LayoutInflater,
		private val todoListItemAdapter: TodoListItemAdapter,
		private val linearLayoutManager: LinearLayoutManager
) {
	fun newMainActivityViewMvc() = MainActivityViewMvc(layoutInflater, null)
	fun newNewTodoListViewMvc(parent: ViewGroup?) = NewTodoListViewMvc(layoutInflater, parent)
	fun newHomeViewMvc(parent: ViewGroup?) = HomeViewMvc(layoutInflater, parent, todoListItemAdapter, linearLayoutManager)
}