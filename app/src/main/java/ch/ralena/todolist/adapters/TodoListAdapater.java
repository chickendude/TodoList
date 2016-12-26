package ch.ralena.todolist.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import ch.ralena.todolist.R;
import ch.ralena.todolist.objects.Todo;

/**
 * Created by crater-windoze on 12/26/2016.
 */

public class TodoListAdapater extends RecyclerView.Adapter {

	private static final int LAST_ITEM = 1;
	private static final int TODO_ITEM = 0;
	ArrayList<Todo> mTodos;

	public TodoListAdapater(ArrayList<Todo> todos) {
		mTodos = todos;
	}

	@Override
	public int getItemViewType(int position) {
		if (position == getItemCount()-1) {
			return LAST_ITEM;
		} else if (position >= 0 && position < getItemCount()) {
			return TODO_ITEM;
		}
		return -1;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todolist, parent, false);
		switch (viewType) {
			case TODO_ITEM:
				return new ViewHolder(view);
			case LAST_ITEM:
				view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_new_todolist, parent, false);
				return new ViewHolderFinal(view);
			default:
				return new ViewHolder(view);
		}
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		if (position < mTodos.size()) {
			if (holder.getItemViewType() == TODO_ITEM) {
				((ViewHolder) holder).bindView(mTodos.get(position));
			}
		} else {
//			((ViewHolderFinal) holder).bindView();
		}
	}

	@Override
	public int getItemCount() {
		return mTodos.size() + 1;
	}

	private class ViewHolder extends RecyclerView.ViewHolder {
		private TextView mTodoLabel;
		private CheckBox mCompletedBox;

		public ViewHolder(View view) {
			super(view);
			mTodoLabel = (TextView) view.findViewById(R.id.todoLabel);
			mCompletedBox = (CheckBox) view.findViewById(R.id.completedBox);
		}

		public void bindView(Todo todo) {
			mTodoLabel.setText(todo.getDescription());
			mCompletedBox.setChecked(todo.isCompleted());
		}
	}

	private class ViewHolderFinal extends RecyclerView.ViewHolder {
		TextView mAddTodoLabel;

		public ViewHolderFinal(View view) {
			super(view);
			mAddTodoLabel = (TextView) view.findViewById(R.id.addTodoLabel);
			mAddTodoLabel.setText("+ Add new item");
		}
	}
}
