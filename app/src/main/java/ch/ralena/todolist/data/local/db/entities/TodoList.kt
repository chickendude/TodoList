package ch.ralena.todolist.data.local.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoList(
		val title: String,
		val position: Int,
//		val todoItems: List<TodoItem> = listOf(),
		val isCompleted: Boolean = false,
		@PrimaryKey(autoGenerate = true)
		val id: Long = 0
)
