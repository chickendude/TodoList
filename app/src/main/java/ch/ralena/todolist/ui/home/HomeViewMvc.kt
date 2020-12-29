package ch.ralena.todolist.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.ralena.todolist.R
import ch.ralena.todolist.data.local.db.entities.TodoList
import ch.ralena.todolist.ui.home.todolists.TodoListItemAdapter
import ch.ralena.todolist.ui.viewmvc.BaseViewMvc

class HomeViewMvc(
		layoutInflater: LayoutInflater,
		parent: ViewGroup?,
		private val todoListItemAdapter: TodoListItemAdapter,
		linearLayoutManager: LinearLayoutManager
) : BaseViewMvc<HomeViewMvc.Listener>(
		layoutInflater,
		parent,
		R.layout.fragment_home
) {
	interface Listener {

	}

	private val recyclerView: RecyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
		adapter = todoListItemAdapter
		layoutManager = linearLayoutManager
	}

	fun bindData(todoLists: List<TodoList>) {
		todoListItemAdapter.updateData(todoLists)
	}

}