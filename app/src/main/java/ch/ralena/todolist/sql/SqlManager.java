package ch.ralena.todolist.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;

import ch.ralena.todolist.objects.TodoList;

/**
 * Created by crater-windoze on 12/22/2016.
 */

/*
	steps for opening/closing a database:
		SQLiteDatabase database = mSqlHelper.getWritableDatabase();
		database.beginTransaction();

		database.setTransactionSuccessful();
		database.endTransaction();
		database.close();
 */
public class SqlManager {
	SqlHelper mSqlHelper;

	public SqlManager(Context context) {
		mSqlHelper = new SqlHelper(context);
	}

	public ArrayList<TodoList> getTodoLists() {
		//TODO: load todo list items
		SQLiteDatabase database = mSqlHelper.getWritableDatabase();
		ArrayList<TodoList> todoLists = new ArrayList<>();

		String title_col = SqlHelper.COL_TODOLIST_TITLE;
		String is_completed_col = SqlHelper.COL_TODOLIST_COMPLETED;
		String id_col = BaseColumns._ID;

		Cursor cursor = database.query(
				SqlHelper.TABLE_TODOLIST,
				new String[] {title_col, is_completed_col, id_col},
				null,
				null,
				null,
				null,
				null);

		if (cursor.moveToFirst()) {
			do {
				long id = getLong(cursor, id_col);
				boolean isCompleted = getInt(cursor, is_completed_col) > 0;
				String title = getString(cursor, title_col);
				TodoList todoList = new TodoList(id, title, isCompleted);
				todoLists.add(todoList);
			} while (cursor.moveToNext());
		}

		cursor.close();
		database.close();
		return todoLists;
	}

	private long getLong(Cursor cursor, String columnName) {
		int index = cursor.getColumnIndex(columnName);
		return cursor.getLong(index);
	}

	private int getInt(Cursor cursor, String columnName) {
		int index = cursor.getColumnIndex(columnName);
		return cursor.getInt(index);
	}

	private String getString(Cursor cursor, String columnName) {
		int index = cursor.getColumnIndex(columnName);
		return cursor.getString(index);
	}

	// returns id
	public long createTodoList(TodoList todoList) {
		SQLiteDatabase database = mSqlHelper.getWritableDatabase();
		database.beginTransaction();

		ContentValues todoListValues = new ContentValues();
		todoListValues.put(SqlHelper.COL_TODOLIST_TITLE, todoList.getTitle());
		todoListValues.put(SqlHelper.COL_TODOLIST_COMPLETED, todoList.isCompleted());
		long id = database.insert(SqlHelper.TABLE_TODOLIST, null, todoListValues);

		database.setTransactionSuccessful();
		database.endTransaction();
		database.close();

		return id;
	}
}
