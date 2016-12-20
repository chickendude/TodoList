package ch.ralena.todolist.objects;

/**
 * Created by crater-windoze on 12/20/2016.
 */

public class Todo {
	private String mDescription;
	private boolean mIsCompleted;

	public Todo(String description) {
		mDescription = description;
		mIsCompleted = false;
	}

	public String getDescription() {
		return mDescription;
	}

	public void setDescription(String description) {
		mDescription = description;
	}

	public boolean isCompleted() {
		return mIsCompleted;
	}

	public void setCompleted(boolean completed) {
		mIsCompleted = completed;
	}
}
