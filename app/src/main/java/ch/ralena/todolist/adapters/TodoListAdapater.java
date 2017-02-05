package ch.ralena.todolist.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ch.ralena.todolist.R;
import ch.ralena.todolist.objects.Todo;

/**
 * Created by crater-windoze on 12/26/2016.
 */

public class TodoListAdapater extends RecyclerView.Adapter {
	private static final String TAG = TodoListAdapater.class.getSimpleName();
	public interface OnTodoItemListener {
		void addNewTodoItem();
		void onCompletedChanged(Todo todo);
		void onDescriptionSet(Todo todo);
	}

	private static final int LAST_ITEM = 1;
	private static final int TODO_ITEM = 0;
	ArrayList<Todo> mTodos;
	OnTodoItemListener mListener;

	// constructor
	public TodoListAdapater(ArrayList<Todo> todos, OnTodoItemListener listener) {
		mTodos = todos;
		mListener = listener;
	}

	@Override
	public int getItemViewType(int position) {
		if (position == getItemCount() - 1) {
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
		}
	}

	@Override
	public int getItemCount() {
		return mTodos.size() + 1;
	}

	private class ViewHolder extends RecyclerView.ViewHolder {
		private RelativeLayout mTodoLayout;
		private LinearLayout mTodoEditLayout;
		private CheckBox mTodoItemBox;
		private TextView mTodoItemLabel;
		private EditText mTodoEdit;
		private TextView mTodoButton;
		private Todo mTodo;
		private boolean mIsSaved;

		public ViewHolder(View view) {
			super(view);
			mTodo = null;
			mTodoLayout = (RelativeLayout) view.findViewById(R.id.todoItemLayout);
			mTodoEditLayout = (LinearLayout) view.findViewById(R.id.editTodoItemLayout);
			mTodoItemBox = (CheckBox) view.findViewById(R.id.todoItemBox);
			mTodoItemBox.setOnCheckedChangeListener(mCheckedChangeListener);
			mTodoItemLabel = (TextView) view.findViewById(R.id.todoItemLabel);
			mTodoEdit = (EditText) view.findViewById(R.id.todoEdit);
			mTodoEdit.setOnFocusChangeListener(mOnFocusChangeListener);
			mTodoButton = (TextView) view.findViewById(R.id.editButton);
			mTodoButton.setOnClickListener(mOnClickListener);
		}

		public void bindView(final Todo todo) {
			mTodo = todo;
			if (!mTodo.getDescription().equals("")) {
				mIsSaved = true;
				mTodoItemLabel.setText(mTodo.getDescription());
				mTodoItemBox.setChecked(mTodo.isCompleted());
				hideEditor();
			} else {
				mIsSaved = false;
				showEditor();
			}
		}

		private void showEditor() {
			mTodoLayout.setVisibility(View.GONE);
			mTodoEditLayout.setVisibility(View.VISIBLE);
		}

		private void hideEditor() {
			mTodoLayout.setVisibility(View.VISIBLE);
			mTodoEditLayout.setVisibility(View.GONE);
		}

		private void saveDescription() {
			if(!mIsSaved) {
				mIsSaved = true;
				String description = mTodoEdit.getText().toString();
				mTodo.setDescription(description);
				mTodoItemLabel.setText(description);
				hideEditor();
				mListener.onDescriptionSet(mTodo);
			}
		}

		// listeners
		CompoundButton.OnCheckedChangeListener mCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
				mTodo.setCompleted(isChecked);
				mListener.onCompletedChanged(mTodo);
			}
		};

		View.OnFocusChangeListener mOnFocusChangeListener = new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View view, boolean hasFocus) {
				if (!hasFocus) {
					saveDescription();
				}
			}
		};

		View.OnClickListener mOnClickListener = new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				saveDescription();
			}
		};
	}

	private class ViewHolderFinal extends RecyclerView.ViewHolder {
		TextView mAddTodoLabel;

		public ViewHolderFinal(View view) {
			super(view);
			mAddTodoLabel = (TextView) view.findViewById(R.id.addTodoLabel);
			mAddTodoLabel.setText("+ Add new item");
			mAddTodoLabel.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					mListener.addNewTodoItem();
				}
			});
		}
	}
}
