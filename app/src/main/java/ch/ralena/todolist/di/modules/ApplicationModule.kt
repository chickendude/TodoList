package ch.ralena.todolist.di.modules

import android.app.Application
import androidx.room.Room
import ch.ralena.todolist.data.local.db.AppDatabase
import ch.ralena.todolist.di.AppScope
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(
		private val application: Application
) {

	@Provides
	fun application() = application

	@Provides
	@AppScope
	fun database(): AppDatabase = Room.databaseBuilder(
			application,
			AppDatabase::class.java,
			"todo_database"
	).build()

	@Provides
	@AppScope
	fun todoListDao(database: AppDatabase) = database.todoListDao()
}