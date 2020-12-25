package ch.ralena.todolist.usecases

import ch.ralena.todolist.data.local.db.dao.TodoListDao
import ch.ralena.todolist.data.local.db.entities.TodoList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.util.concurrent.CancellationException

class CreateTodoListUseCase(
		val todoListDao: TodoListDao
) {

	sealed class Result {
		data class Success(val todoList: TodoList) : Result()
		object Failure : Result()
	}


	suspend fun createTodoList(title: String) =
			withContext(Dispatchers.IO) {
				try {
					val count = todoListDao.getAll().count()
					todoListDao.insert(TodoList(title, count))
				} catch (e: Exception) {
					if (e is CancellationException) {
						return@withContext Result.Failure
					} else {
						throw e
					}
				}
			}
}