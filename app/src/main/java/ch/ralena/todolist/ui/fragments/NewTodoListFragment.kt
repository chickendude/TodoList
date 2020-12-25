package ch.ralena.todolist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import ch.ralena.todolist.data.local.db.TodoListRepository
import ch.ralena.todolist.ui.base.BaseFragment
import ch.ralena.todolist.ui.viewmvc.ViewMvcFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewTodoListFragment : BaseFragment(), NewTodoListViewMvc.Listener {
	@Inject
	lateinit var viewMvcFactory: ViewMvcFactory

	@Inject
	lateinit var todoListRepository: TodoListRepository

	private lateinit var viewMvc: NewTodoListViewMvc

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		injector.inject(this)
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		viewMvc = viewMvcFactory.newNewTodoListViewMvc(container)
		viewMvc.registerListener(this)
		return viewMvc.rootView
	}

	override fun onSubmitted(title: String) {
		coroutineScope.launch {
			try {
				Toast.makeText(context, title, Toast.LENGTH_LONG).show()
				todoListRepository.createTodoList(title)
			} finally {

			}
		}
	}
}