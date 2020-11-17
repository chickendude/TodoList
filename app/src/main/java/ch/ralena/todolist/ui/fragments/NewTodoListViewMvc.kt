package ch.ralena.todolist.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import ch.ralena.todolist.R
import ch.ralena.todolist.ui.viewmvc.BaseViewMvc
import javax.inject.Inject

class NewTodoListViewMvc(
		layoutInflater: LayoutInflater,
		parent: ViewGroup?
) : BaseViewMvc<NewTodoListViewMvc.Listener>(
		layoutInflater,
		parent,
		R.layout.fragment_newtodolist
) {
	interface Listener {

	}

}