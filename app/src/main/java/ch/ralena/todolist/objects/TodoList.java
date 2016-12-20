package ch.ralena.todolist.objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by crater-windoze on 12/20/2016.
 */

public class TodoList {
	private List<Todo> mTodoList;
	private boolean mIsCompleted;

	public TodoList() {
		mTodoList = new ArrayList<>();
	}

	public void add(Todo todo) {
		mTodoList.add(todo);
	}

	public void remove(Todo todo) {
		mTodoList.remove(todo);
	}

	public List<Todo> getTodoList() {
		return mTodoList;
	}

	public void setTodoList(List<Todo> todoList) {
		mTodoList = todoList;
	}

	public boolean isCompleted() {
		return mIsCompleted;
	}

	public void setCompleted(boolean completed) {
		mIsCompleted = completed;
	}
}
