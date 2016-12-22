package ch.ralena.todolist.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ch.ralena.todolist.R;
import ch.ralena.todolist.objects.TodoList;

/**
 * Created by crater-windoze on 12/20/2016.
 */

public class MainAdapter extends RecyclerView.Adapter {
	// public interfaces
	public interface OnDeleteClickedListener {
		void onDeleteClicked(TodoList todoList);
	}

	List<TodoList> mTodoLists;
	OnDeleteClickedListener mListener;

	public MainAdapter(List<TodoList> todoLists, OnDeleteClickedListener listener) {
		mTodoLists = todoLists;
		mListener = listener;
	}

	public void updateTodoList(List<TodoList> todoLists) {
		mTodoLists = todoLists;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_todolist, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		((ViewHolder)holder).bindView(mTodoLists.get(position));

	}

	@Override
	public int getItemCount() {
		return mTodoLists.size();
	}

	private class ViewHolder extends RecyclerView.ViewHolder {
		TextView mTitleLabel;
		ImageView mDeleteButton;

		public ViewHolder(View view) {
			super(view);
			mTitleLabel = (TextView) view.findViewById(R.id.titleLabel);
			mDeleteButton = (ImageView) view.findViewById(R.id.deleteImage);
		}

		public void bindView(final TodoList todoList) {
			mTitleLabel.setText(todoList.getTitle());
			mDeleteButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					mListener.onDeleteClicked(todoList);
				}
			});

		}
	}
}
