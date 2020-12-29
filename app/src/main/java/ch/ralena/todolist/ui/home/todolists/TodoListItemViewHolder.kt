package ch.ralena.todolist.ui.home.todolists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import ch.ralena.todolist.R
import ch.ralena.todolist.data.local.db.entities.TodoList
import ch.ralena.todolist.ui.base.BaseItemViewHolder

class TodoListItemViewHolder(
		parent: ViewGroup
) : BaseItemViewHolder<TodoList, TodoListItemViewHolder.Listener>(
		R.layout.item_todolist,
		parent
) {
	interface Listener {
		fun onClicked(item: TodoList)
	}

	lateinit var todoList : TodoList

	private val description: TextView = itemView.findViewById(R.id.tvDescription)
	private val completed: CheckBox = itemView.findViewById(R.id.cbCompleted)

	init {
		description.setOnClickListener {
			for (listener in listeners) {
				listener.onClicked(todoList)
			}
		}
	}

	override fun bind(data: TodoList) {
		todoList = data
		description.text = data.title
		completed.isChecked = data.isCompleted
	}

}