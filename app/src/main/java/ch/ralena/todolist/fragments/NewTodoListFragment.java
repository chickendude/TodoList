package ch.ralena.todolist.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import ch.ralena.todolist.R;
import ch.ralena.todolist.objects.TodoList;

/**
 * Created by crater-windoze on 12/20/2016.
 */

public class NewTodoListFragment extends DialogFragment {

	private SubmitNewTodoListListener listener;

	public interface SubmitNewTodoListListener {
		void onSubmittedNewTodoList(TodoList todoList);
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		listener = (SubmitNewTodoListListener) context;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// make dialog disappear when you click outside
		final Dialog dialog = getDialog();
		dialog.setCanceledOnTouchOutside(true);
		// set up views
		final View view = inflater.inflate(R.layout.fragment_new_todo_list, container);
		// set up edittext listener
		final EditText editText = (EditText) view.findViewById(R.id.titleEdit);
		editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
				boolean handled = false;
				if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
					String title = editText.getText().toString();
					if (!title.equals("")) {
						TodoList todoList = new TodoList(title);
						listener.onSubmittedNewTodoList(todoList);
						dialog.dismiss();
						handled = true;
					}
				}
				return handled;
			}
		});
		// make keyboard popup
		editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View view, boolean hasFocus) {
				if (hasFocus) {
					dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
				}
			}
		});
		return view;
	}
}
