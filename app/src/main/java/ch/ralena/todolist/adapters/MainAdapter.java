package ch.ralena.todolist.adapters;

import android.content.res.Resources;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
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

// TODO: Convert the checkbox into a button when we are in edit mode
public class MainAdapter extends RecyclerView.Adapter {
	public static final String TAG = MainAdapter.class.getSimpleName();
	// public interfaces
	public interface OnDataChangedListener {
		void onDeleteClicked(TodoList todoList);
		void onTitleEdited(TodoList todoList);
		void onCompletionStatusChanged(TodoList todoList);
	}

	public interface OnItemClickedListener {
		void onOpenTodoList(TodoList todoList);
	}

	List<TodoList> mTodoLists;
	OnDataChangedListener mDataListener;
	OnItemClickedListener mClickedListener;

	public MainAdapter(List<TodoList> todoLists, OnDataChangedListener dataListener, OnItemClickedListener clickedListener) {
		mTodoLists = todoLists;
		mDataListener = dataListener;
		mClickedListener = clickedListener;
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
		// give a margin to last item, otherwise FAB will cover up the checkbox
		int bottomMargin = 0;
		if (position + 1 == getItemCount()) {
			bottomMargin = (int) (72 * Resources.getSystem().getDisplayMetrics().density);
		}
		ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams();
		params.setMargins(params.leftMargin, params.topMargin, params.rightMargin, bottomMargin);
		// bind the view
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
			mTitleLabel.setOnClickListener(onClickListener);
			mTitleLabel.setOnLongClickListener(longClickListener);
			mCompletedCheckBox = (CheckBox) view.findViewById(R.id.completedButton);
			mCompletedCheckBox.setOnCheckedChangeListener(checkedChangeListener);
			mTitleEdit = (EditText) view.findViewById(R.id.titleEdit);
			mTitleEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
				@Override
				public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
					boolean handled = false;
					if (actionId == R.id.titleEditReturn || actionId == EditorInfo.IME_NULL) {
						saveChanges();
						handled = true;
					}
					return handled;
				}
			});
		}

		public void bindView(final TodoList todoList) {
			mTodoList = todoList;
			mCompletedCheckBox.setChecked(mTodoList.isCompleted());
			hideEditor();
		}

		CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
				mTodoList.setCompleted(isChecked);
				mDataListener.onCompletionStatusChanged(mTodoList);
			}
		};

		View.OnClickListener onClickListener = new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// this gets passed to MainFragment
				mClickedListener.onOpenTodoList(mTodoList);
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
						mDataListener.onDeleteClicked(mTodoList);
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
			mDataListener.onTitleEdited(mTodoList);
			hideEditor();
		}
	}
}
