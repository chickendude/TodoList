package ch.ralena.todolist.objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by crater-windoze on 12/20/2016.
 */

public class TodoList implements Parcelable {
	private String mTitle;
	private ArrayList<Todo> mTodoList;
	private boolean mIsCompleted;

	public TodoList(String title) {
		mTitle = title;
		mTodoList = new ArrayList<>();
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

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}

	public List<Todo> getTodoList() {
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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeInt(mIsCompleted ? 1 : 0);	// mIsCompleted
		parcel.writeString(mTitle);	// mTitle
		parcel.writeTypedArray((Todo[])mTodoList.toArray(), 0);
	}
}
