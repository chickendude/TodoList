package ch.ralena.todolist.data.local.db.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class TodoList(
		val title: String,
		val position: Int,
//		val todoItems: List<TodoItem> = listOf(),
		val isCompleted: Boolean = false,
		@PrimaryKey(autoGenerate = true)
		val id: Long = 0
) : Parcelable
