package ch.ralena.todolist.objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by crater-windoze on 12/20/2016.
 */

public class TodoList implements Parcelable {
	private long mId;
	private String mTitle;
	private ArrayList<Todo> mTodoList;
	private boolean mIsCompleted;
	private int mPosition;

	public TodoList(String title) {
		mTitle = title;
		mTodoList = new ArrayList<>();
	}

	public TodoList(long id, String title, boolean isCompleted, int position) {
		mId = id;
		mTitle = title;
		mTodoList = new ArrayList<>();
		mIsCompleted = isCompleted;
		mPosition = position;
	}

	protected TodoList(Parcel in) {
		mTitle = in.readString();
		mTodoList = in.createTypedArrayList(Todo.CREATOR);
		mIsCompleted = in.readByte() != 0;
	}

	public static final Creator<TodoList> CREATOR = new Creator<TodoList>() {
		@Override
		public TodoList createFromParcel(Parcel in) {
			return new TodoList(in);
		}

		@Override
		public TodoList[] newArray(int size) {
			return new TodoList[size];
		}
	};

	public void add(Todo todo) {
		mTodoList.add(todo);
	}

	public void remove(Todo todo) {
		mTodoList.remove(todo);
	}

	public long getId() {
		return mId;
	}

	public void setId(long id) {
		mId = id;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}

	public ArrayList<Todo> getTodoItems() {
		return mTodoList;
	}

	public void setTodoList(ArrayList<Todo> todoList) {
		mTodoList = todoList;
	}

	public boolean isCompleted() {
		return mIsCompleted;
	}

	public void setCompleted(boolean completed) {
		mIsCompleted = completed;
	}

	public int getPosition() {
		return mPosition;
	}

	public void setPosition(int position) {
		mPosition = position;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeInt(mIsCompleted ? 1 : 0);    // mIsCompleted
		parcel.writeString(mTitle); // mTitle
		parcel.writeTypedArray((Todo[]) mTodoList.toArray(new Todo[mTodoList.size()]), 0);
	}
}
