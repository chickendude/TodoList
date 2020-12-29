package ch.ralena.todolist.ui.home.todolists

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import ch.ralena.todolist.R
import ch.ralena.todolist.data.local.db.entities.TodoList
import ch.ralena.todolist.ui.activities.MainActivity
import ch.ralena.todolist.ui.base.BaseAdapter
import ch.ralena.todolist.ui.fragments.TodoListFragment
import ch.ralena.todolist.ui.home.HomeFragment3

class TodoListItemAdapter(
		todoLists: ArrayList<TodoList>,
		private val context: Context
) : BaseAdapter<TodoList, TodoListItemViewHolder.Listener, TodoListItemViewHolder>(
		todoLists
), TodoListItemViewHolder.Listener {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
			TodoListItemViewHolder(parent)
					.apply { registerListener(this@TodoListItemAdapter) }

	override fun onClicked(item: TodoList) {
		Toast.makeText(context, item.title, Toast.LENGTH_SHORT).show()

		val todoListFragment = TodoListFragment()


		// pull our todolist from the parcelables
		val bundle = Bundle()
		bundle.putParcelable(HomeFragment3.TAG_TODO_LISTS, item)
		todoListFragment.arguments = bundle


		(context as MainActivity).supportFragmentManager.beginTransaction()
				.replace(R.id.placeHolder, todoListFragment)
				.addToBackStack(null)
				.commit()
	}
}