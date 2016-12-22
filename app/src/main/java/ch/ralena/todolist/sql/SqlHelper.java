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
	private static final int DB_VERSION = 1;

	//SQL elements: TODO_LIST
	public static final String TABLE_TODOLIST = "TODOLIST";
	public static final String COL_TODOLIST_TITLE = "TITLE";
	public static final String COL_TODOLIST_COMPLETED = "COMPLETED";

	// SQL statements
	private static final String CREATE_TODOLIST =
			"CREATE TABLE " + TABLE_TODOLIST +
					"( " + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					COL_TODOLIST_TITLE + " TEXT, " +
					COL_TODOLIST_COMPLETED + " INTEGER " +
					" )";
	private static final String CREATE_TODO_ITEM = "";

	public SqlHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		sqLiteDatabase.execSQL(CREATE_TODOLIST);
//		sqLiteDatabase.execSQL(CREATE_TODO_ITEM);

	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

	}
}
