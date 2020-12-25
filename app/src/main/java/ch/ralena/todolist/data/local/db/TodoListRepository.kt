package ch.ralena.todolist.data.local.db

import ch.ralena.todolist.data.local.db.dao.TodoListDao
import ch.ralena.todolist.data.local.db.entities.TodoList
import ch.ralena.todolist.usecases.CreateTodoListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.util.concurrent.CancellationException
import javax.inject.Inject

class TodoListRepository @Inject constructor(
		private val todoListDao: TodoListDao
) {
	suspend fun createTodoList(title: String) =
			withContext(Dispatchers.IO) {
				try {
					val count = todoListDao.getAll().count()
					todoListDao.insert(TodoList(title, count))
				} catch (e: Exception) {
					if (e is CancellationException) {
						return@withContext CreateTodoListUseCase.Result.Failure
					} else {
						throw e
					}
				}
			}

	suspend fun getAll(): List<TodoList> =
			withContext(Dispatchers.IO) {
				return@withContext todoListDao.getAll()
			}
}