package ch.ralena.todolist.ui.home.todolists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ch.ralena.todolist.R
import ch.ralena.todolist.data.local.db.entities.TodoList

class TodoListItemAdapter(private val todoLists: ArrayList<TodoList>) : RecyclerView.Adapter<TodoListItemAdapter.ViewHolder>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_todolist, parent, false)
		return ViewHolder(itemView)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(todoLists[position])

	override fun getItemCount(): Int {
		return todoLists.size
	}

	fun updateData(data: List<TodoList>) {
		todoLists.clear()
		todoLists.addAll(data)
		notifyDataSetChanged()
	}

	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		private val title: TextView = itemView.findViewById(R.id.todoItemLabel)
		private val completed: CheckBox = itemView.findViewById(R.id.todoItemBox)

		fun bind(todoList: TodoList) {
			title.text = todoList.title
			completed.isChecked = todoList.isCompleted
		}
	}
}