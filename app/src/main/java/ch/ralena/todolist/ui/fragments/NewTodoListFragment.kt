package ch.ralena.todolist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ch.ralena.todolist.ui.base.BaseFragment
import ch.ralena.todolist.ui.viewmvc.ViewMvcFactory
import javax.inject.Inject

class NewTodoListFragment : BaseFragment() {
	@Inject
	lateinit var viewMvcFactory: ViewMvcFactory

	private lateinit var viewMvc: NewTodoListViewMvc

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		injector.inject(this)
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		viewMvc = viewMvcFactory.newNewTodoListViewMvc(container)
		return viewMvc.rootView
	}
}