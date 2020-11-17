package ch.ralena.todolist.ui.viewmvc

import android.view.LayoutInflater
import android.view.ViewGroup
import ch.ralena.todolist.ui.activities.MainActivityViewMvc
import ch.ralena.todolist.ui.fragments.NewTodoListViewMvc
import javax.inject.Inject

open class ViewMvcFactory @Inject constructor(
		private val layoutInflater: LayoutInflater,
) {
	fun newMainActivityViewMvc() = MainActivityViewMvc(layoutInflater, null)
	fun newNewTodoListViewMvc(parent: ViewGroup?) = NewTodoListViewMvc(layoutInflater, parent)
}