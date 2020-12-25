package ch.ralena.todolist.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ch.ralena.todolist.data.local.db.dao.TodoItemDao
import ch.ralena.todolist.data.local.db.dao.TodoListDao
import ch.ralena.todolist.data.local.db.entities.TodoItem
import ch.ralena.todolist.data.local.db.entities.TodoList

@Database(
		entities = [TodoItem::class, TodoList::class],
		version = 1
)
abstract class AppDatabase : RoomDatabase() {
	abstract fun todoItemDao(): TodoItemDao
	abstract fun todoListDao(): TodoListDao
}