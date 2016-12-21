package ch.ralena.todolist.objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by crater-windoze on 12/20/2016.
 */

public class Todo implements Parcelable {
	private String mDescription;
	private boolean mIsCompleted;

	public Todo(String description) {
		mDescription = description;
		mIsCompleted = false;
	}

	protected Todo(Parcel in) {
		mDescription = in.readString();
		mIsCompleted = in.readByte() != 0;
	}

	public static final Creator<Todo> CREATOR = new Creator<Todo>() {
		@Override
		public Todo createFromParcel(Parcel in) {
			return new Todo(in);
		}

		@Override
		public Todo[] newArray(int size) {
			return new Todo[size];
		}
	};

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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(mDescription);
		parcel.writeByte((byte) (mIsCompleted ? 1 : 0));
	}
}
