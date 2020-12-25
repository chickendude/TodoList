package ch.ralena.todolist.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import ch.ralena.todolist.R
import ch.ralena.todolist.data.local.db.dao.TodoListDao
import ch.ralena.todolist.ui.viewmvc.BaseViewMvc
import com.google.android.material.textfield.TextInputEditText
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
		fun onSubmitted(title: String)
	}

	private val createButton: Button = findViewById(R.id.createButton)
	private val todoListTitle: TextInputEditText = findViewById(R.id.todoListTitle)

	init {
		createButton.setOnClickListener {
			for (listener in listeners) {
				listener.onSubmitted(todoListTitle.text.toString())
			}
		}
	}

}