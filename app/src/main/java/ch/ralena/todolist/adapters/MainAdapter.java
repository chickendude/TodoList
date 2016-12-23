package ch.ralena.todolist.adapters;

import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import ch.ralena.todolist.R;
import ch.ralena.todolist.objects.TodoList;

/**
 * Created by crater-windoze on 12/20/2016.
 */

public class MainAdapter extends RecyclerView.Adapter {
	// public interfaces
	public interface OnDataChangedListener {
		void onDeleteClicked(TodoList todoList);
		void onTitleEdited(TodoList todoList);
		void onCompletionStatusChanged(TodoList todoList);
	}

	List<TodoList> mTodoLists;
	OnDataChangedListener mListener;

	public MainAdapter(List<TodoList> todoLists, OnDataChangedListener listener) {
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
		((ViewHolder) holder).bindView(mTodoLists.get(position));
	}

	@Override
	public int getItemCount() {
		return mTodoLists.size();
	}

	private class ViewHolder extends RecyclerView.ViewHolder {
		TextView mTitleLabel;
		EditText mTitleEdit;
		TodoList mTodoList;
		CheckBox mCompletedCheckBox;

		public ViewHolder(View view) {
			super(view);
			mTodoList = null;
			mTitleLabel = (TextView) view.findViewById(R.id.titleLabel);
			mTitleLabel.setOnLongClickListener(longClickListener);
			mCompletedCheckBox = (CheckBox) view.findViewById(R.id.completedButton);
			mCompletedCheckBox.setOnCheckedChangeListener(checkedChangeListener);
			mTitleEdit = (EditText) view.findViewById(R.id.titleEdit);
		}

		public void bindView(final TodoList todoList) {
			mTodoList = todoList;
			mCompletedCheckBox.setChecked(mTodoList.isCompleted());
			hideEditor();
		}

		CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
				mTodoList.setCompleted(b);
				mListener.onCompletionStatusChanged(mTodoList);
			}
		};

		View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View view) {
				// create a popup context menu
				PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
				popupMenu.inflate(R.menu.context_menu_todolist);
				popupMenu.setOnMenuItemClickListener(menuItemClickListener);
				popupMenu.show();
				return false;
			}
		};

		PopupMenu.OnMenuItemClickListener menuItemClickListener = new PopupMenu.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				int id = item.getItemId();
				switch (id) {
					case R.id.editTitle:
						setupEditText();
						break;
					case R.id.delete:
						mListener.onDeleteClicked(mTodoList);
						break;
					default:
						break;
				}
				return false;
			}
		};

		private void hideEditor() {
			mTitleLabel.setVisibility(View.VISIBLE);
			mTitleLabel.setText(mTodoList.getTitle());
			mTitleEdit.setVisibility(View.GONE);
		}

		private void setupEditText() {
			mTitleEdit.setText(mTitleLabel.getText());
			mTitleEdit.setVisibility(View.VISIBLE);
			mTitleEdit.requestFocus();
			mTitleLabel.setVisibility(View.GONE);
			mTitleEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
				@Override
				public void onFocusChange(View view, boolean hasFocus) {
					if (!hasFocus) {
						saveChanges();
					}
				}
			});
		}

		private void saveChanges() {
			mTodoList.setTitle(mTitleEdit.getText().toString());
			mListener.onTitleEdited(mTodoList);
			hideEditor();
		}
	}
}
