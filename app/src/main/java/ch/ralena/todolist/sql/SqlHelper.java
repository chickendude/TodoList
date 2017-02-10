package ch.ralena.todolist.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by crater-windoze on 12/20/2016.
 */

public class SqlHelper extends SQLiteOpenHelper {
	public static final String DB_NAME = "todolist.db";
	private static final int DB_VERSION = 3;

	//SQL elements: TODO_LIST
	public static final String TABLE_TODOLIST = "TODOLIST";
	public static final String COL_TODOLIST_TITLE = "TITLE";
	public static final String COL_TODOLIST_COMPLETED = "COMPLETED";
	public static final String COL_TODOLIST_POSITION = "POSITION";

	//SQL elements: TODO_ITEM
	public static final String TABLE_TODOITEM = "TODOITEM";
	public static final String COL_TODOITEM_DESCRIPTION = "DESCRIPTION";
	public static final String COL_TODOITEM_COMPLETED = "COMPLETED";
	public static final String COL_TODOITEM_FOREIGN_KEY_TODOLIST = "TODOLIST_ID";

	// SQL statements
	private static final String CREATE_TODOLIST =
			"CREATE TABLE " + TABLE_TODOLIST +
					"( " + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					COL_TODOLIST_TITLE + " TEXT, " +
					COL_TODOLIST_COMPLETED + " INTEGER, " +
					COL_TODOLIST_POSITION + " INTEGER " +
					" )";
	private static final String CREATE_TODO_ITEM =
			"CREATE TABLE " + TABLE_TODOITEM +
					"( " + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					COL_TODOITEM_DESCRIPTION + " TEXT, " +
					COL_TODOITEM_COMPLETED + " INTEGER, " +
					COL_TODOITEM_FOREIGN_KEY_TODOLIST + " INTEGER, " +
					"FOREIGN KEY(" + COL_TODOITEM_FOREIGN_KEY_TODOLIST + ") REFERENCES " + TABLE_TODOLIST + "(_ID)" +
					" )";

	public SqlHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}


	// Updates
	private static final String ADD_POSITION_COLUMN =
			"ALTER TABLE " + TABLE_TODOLIST + " ADD " + COL_TODOLIST_POSITION + " INTEGER ";

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		sqLiteDatabase.execSQL(CREATE_TODOLIST);
		sqLiteDatabase.execSQL(CREATE_TODO_ITEM);
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
		switch (oldVersion) {
			case 1:
				sqLiteDatabase.execSQL(CREATE_TODO_ITEM);
				sqLiteDatabase.execSQL(ADD_POSITION_COLUMN);
				break;
			case 2:
				sqLiteDatabase.execSQL(ADD_POSITION_COLUMN);
				break;
		}
	}
}
