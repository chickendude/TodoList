package ch.ralena.todolist.data.local.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;

import ch.ralena.todolist.data.models.Todo;
import ch.ralena.todolist.data.models.TodoList;

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
	private static final String TAG = SqlManager.class.getSimpleName();
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
		String position_col = SqlHelper.COL_TODOLIST_POSITION;

		Cursor cursor = database.query(
				SqlHelper.TABLE_TODOLIST,
				new String[]{title_col, is_completed_col, id_col, position_col},
				null,
				null,
				null,
				null,
				position_col + " ASC");

		if (cursor.moveToFirst()) {
			do {
				long id = getLong(cursor, id_col);
				int position = getInt(cursor, position_col);
				boolean isCompleted = getInt(cursor, is_completed_col) > 0;
				String title = getString(cursor, title_col);
				TodoList todoList = new TodoList(id, title, isCompleted, position);
				todoList.setTodoList(getTodoItems(database, id));
				todoLists.add(todoList);
			} while (cursor.moveToNext());
		}

		cursor.close();


//		database.close();
		return todoLists;
	}

	private ArrayList<Todo> getTodoItems(SQLiteDatabase database, long id) {
		ArrayList<Todo> todoList = new ArrayList<>();
		Cursor itemCursor = database.rawQuery(
				"SELECT * FROM " + SqlHelper.TABLE_TODOITEM +
						" WHERE " + SqlHelper.COL_TODOITEM_FOREIGN_KEY_TODOLIST + " = " + id, null);
		if (itemCursor.moveToFirst()) {
			do {
				long todoId = getLong(itemCursor, BaseColumns._ID);
				boolean isCompleted = getInt(itemCursor, SqlHelper.COL_TODOITEM_COMPLETED) > 0;
				String description = getString(itemCursor, SqlHelper.COL_TODOITEM_DESCRIPTION);
				Todo todo = new Todo(todoId, description, isCompleted);
				todoList.add(todo);
			} while (itemCursor.moveToNext());
		}
		itemCursor.close();
		return todoList;
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

	public void deleteTodoList(TodoList todoList) {
		SQLiteDatabase database = mSqlHelper.getWritableDatabase();
		database.beginTransaction();

		String whereClause = BaseColumns._ID + "=?";
		String[] whereArgs = new String[]{String.valueOf(todoList.getId())};
		database.delete(SqlHelper.TABLE_TODOLIST, whereClause, whereArgs);

		database.setTransactionSuccessful();
		database.endTransaction();
//		database.close();
	}

	// returns id
	public long createTodoList(TodoList todoList) {
		SQLiteDatabase database = mSqlHelper.getWritableDatabase();
		database.beginTransaction();

		ContentValues todoListValues = new ContentValues();
		todoListValues.put(SqlHelper.COL_TODOLIST_TITLE, todoList.getTitle());
		todoListValues.put(SqlHelper.COL_TODOLIST_COMPLETED, todoList.isCompleted());
		todoListValues.put(SqlHelper.COL_TODOLIST_POSITION, todoList.getPosition());
		long id = database.insert(SqlHelper.TABLE_TODOLIST, null, todoListValues);

		database.setTransactionSuccessful();
		database.endTransaction();
//		database.close();

		return id;
	}

	public void closeDb() {
		mSqlHelper.getWritableDatabase().close();
	}

	public long createTodoListItem(Todo todo, long todoListId) {
		SQLiteDatabase database = mSqlHelper.getWritableDatabase();
		database.beginTransaction();

		ContentValues todoItemValues = new ContentValues();
		todoItemValues.put(SqlHelper.COL_TODOITEM_DESCRIPTION, todo.getDescription());
		todoItemValues.put(SqlHelper.COL_TODOITEM_COMPLETED, todo.isCompleted());
		todoItemValues.put(SqlHelper.COL_TODOITEM_FOREIGN_KEY_TODOLIST, todoListId);
		long id = database.insert(SqlHelper.TABLE_TODOITEM, null, todoItemValues);

		database.setTransactionSuccessful();
		database.endTransaction();
//		database.close();

		return id;
	}

	public void updateTodoListTitle(TodoList todoList) {
		ContentValues todoListValues = new ContentValues();
		todoListValues.put(SqlHelper.COL_TODOLIST_TITLE, todoList.getTitle());
		updateTodoList(todoList, todoListValues);
	}

	public void updateTodoListCompleted(TodoList todoList) {
		ContentValues todoListValues = new ContentValues();
		todoListValues.put(SqlHelper.COL_TODOLIST_COMPLETED, todoList.isCompleted());
		updateTodoList(todoList, todoListValues);
	}

	public void updateTodoListPosition(TodoList todoList) {
		ContentValues todoListValues = new ContentValues();
		todoListValues.put(SqlHelper.COL_TODOLIST_POSITION, todoList.getPosition());
		updateTodoList(todoList, todoListValues);
	}

	private void updateTodoList(TodoList todoList, ContentValues todoListValues) {
		SQLiteDatabase database = mSqlHelper.getWritableDatabase();
		database.beginTransaction();

		String whereClause = BaseColumns._ID + "=?";
		String[] whereArgs = new String[]{String.valueOf(todoList.getId())};
		database.update(SqlHelper.TABLE_TODOLIST, todoListValues, whereClause, whereArgs);

		database.setTransactionSuccessful();
		database.endTransaction();
//		database.close();
	}

	public void updateTodoItemCompleted(Todo todo) {
		ContentValues todoItemValues = new ContentValues();
		todoItemValues.put(SqlHelper.COL_TODOITEM_COMPLETED, todo.isCompleted());
		updateTodoItem(todo, todoItemValues);
	}

	private void updateTodoItem(Todo todo, ContentValues todoItemValues) {
		SQLiteDatabase database = mSqlHelper.getWritableDatabase();
		database.beginTransaction();

		String whereClause = BaseColumns._ID + "=?";
		String[] whereArgs = new String[]{String.valueOf(todo.getId())};
		database.update(SqlHelper.TABLE_TODOITEM, todoItemValues, whereClause, whereArgs);

		database.setTransactionSuccessful();
		database.endTransaction();
//		database.close();
	}

}
