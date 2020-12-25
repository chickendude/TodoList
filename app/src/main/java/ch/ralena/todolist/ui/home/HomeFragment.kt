package ch.ralena.todolist.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ch.ralena.todolist.data.local.db.TodoListRepository
import ch.ralena.todolist.ui.base.BaseFragment
import ch.ralena.todolist.ui.viewmvc.ViewMvcFactory
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeFragment : BaseFragment(), HomeViewMvc.Listener {
	@Inject
	lateinit var viewMvcFactory: ViewMvcFactory

	@Inject
	lateinit var todoListRepository: TodoListRepository

	private lateinit var viewMvc: HomeViewMvc

	override fun onCreate(savedInstanceState: Bundle?) {
		injector.inject(this)
		super.onCreate(savedInstanceState)
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		viewMvc = viewMvcFactory.newHomeViewMvc(container)
		viewMvc.registerListener(this)
		return viewMvc.rootView
	}

	override fun onStart() {
		super.onStart()
		viewMvc.registerListener(this)
		fetchData()
	}

	override fun onStop() {
		super.onStop()
		coroutineScope.coroutineContext.cancelChildren()
		viewMvc.unregisterListener(this)
	}

	private fun fetchData() {
		coroutineScope.launch {
			try {
				// show progress animation
				val todoLists = todoListRepository.getAll()
				viewMvc.bindData(todoLists)
			} finally {
				// stop progress anim
			}
		}
	}
}