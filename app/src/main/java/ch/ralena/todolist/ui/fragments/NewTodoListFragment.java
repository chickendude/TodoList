package ch.ralena.todolist.ui.fragments;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import ch.ralena.todolist.R;
import ch.ralena.todolist.data.models.TodoList;

/**
 * Created by crater-windoze on 12/20/2016.
 */

public class NewTodoListFragment extends DialogFragment {

	private SubmitNewTodoListListener listener;
	private EditText mEditText;

	public interface SubmitNewTodoListListener {
		void onSubmittedNewTodoList(TodoList todoList);
		void onCancelNewTodoList();
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		listener = (SubmitNewTodoListListener) context;
	}

	// for API<23
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		listener = (SubmitNewTodoListListener) activity;
	}


	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getDialog()
				.getWindow()
				.getAttributes()
				.windowAnimations = R.style.NewTodoListAnimation;
	}

	@Override
	public void onDismiss(DialogInterface dialog) {
		listener.onCancelNewTodoList();
		super.onDismiss(dialog);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// make dialog disappear when you click outside
		getDialog().setCanceledOnTouchOutside(true);
		// set up views
		View view = inflater.inflate(R.layout.fragment_new_todolist, container);

		// set up onclick listener for create button
		Button button = (Button) view.findViewById(R.id.createButton);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				sendData();
			}
		});

		// set up edittext listener for pressing Enter
		mEditText = (EditText) view.findViewById(R.id.titleEdit);
		mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
				boolean handled = false;
				if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
					sendData();
				}
				return handled;
			}
		});
		// make keyboard popup
		mEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View view, boolean hasFocus) {
				if (hasFocus) {
					getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
				}
			}
		});
		return view;
	}

	public void sendData() {
		String title = mEditText.getText().toString();
		if (!title.equals("")) {
			TodoList todoList = new TodoList(title);
			listener.onSubmittedNewTodoList(todoList);
		}
		getDialog().dismiss();
	}
}
