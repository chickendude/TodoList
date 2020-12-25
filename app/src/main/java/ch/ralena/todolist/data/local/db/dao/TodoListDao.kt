package ch.ralena.todolist.data.local.db.dao

import androidx.room.*
import ch.ralena.todolist.data.local.db.entities.TodoItem
import ch.ralena.todolist.data.local.db.entities.TodoList

@Dao
interface TodoListDao {
	@Query("SELECT * FROM todolist")
	fun getAll(): List<TodoList>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(todoList: TodoList): Long

	@Delete
	fun delete(todoList: TodoList)
}