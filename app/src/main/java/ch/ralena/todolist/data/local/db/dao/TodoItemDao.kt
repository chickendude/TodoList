package ch.ralena.todolist.data.local.db.dao

import androidx.room.*
import ch.ralena.todolist.data.local.db.entities.TodoItem

@Dao
interface TodoItemDao {
	@Query("SELECT * FROM todoitem")
	fun getAll(): List<TodoItem>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(todoItem: TodoItem): Long

	@Delete
	fun delete(todoItem: TodoItem)
}