package ch.ralena.todolist.data.local.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoItem(
		val description: String,
		val todoListId: Long,
		val isCompleted: Boolean = false,
		@PrimaryKey(autoGenerate = true)
		val id: Int = 0
)
