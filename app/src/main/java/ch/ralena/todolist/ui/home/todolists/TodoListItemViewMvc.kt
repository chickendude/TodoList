package ch.ralena.todolist.ui.home.todolists

import android.view.LayoutInflater
import android.view.ViewGroup
import ch.ralena.todolist.R
import ch.ralena.todolist.ui.viewmvc.BaseViewMvc

class TodoListItemViewMvc(
		layoutInflater: LayoutInflater,
		parent: ViewGroup?
): BaseViewMvc<TodoListItemViewMvc.Listener>(
		layoutInflater,
		parent,
		R.layout.item_todolist
) {
	interface Listener {

	}
}