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

	ArrayList<Todo> mTodos;

	public TodoListAdapater(ArrayList<Todo> todos) {
		mTodos = todos;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todolist, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		((ViewHolder)holder).bindView(mTodos.get(position));
	}

	@Override
	public int getItemCount() {
		return mTodos.size();
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
}
