package ch.ralena.todolist.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ch.ralena.todolist.R;
import ch.ralena.todolist.adapters.TodoListAdapater;
import ch.ralena.todolist.data.local.db.entities.TodoList;
import ch.ralena.todolist.data.local.sql.SqlManager;
import ch.ralena.todolist.data.models.Todo;
import ch.ralena.todolist.ui.activities.MainActivity;
import ch.ralena.todolist.ui.home.HomeFragment3;

/**
 * Created by crater-windoze on 12/26/2016.
 */

public class TodoListFragment extends Fragment implements TodoListAdapater.OnTodoItemListener,
		View.OnClickListener {
	private static final String TAG = TodoListFragment.class.getSimpleName();
	TodoList mTodoList;
	private SqlManager mSqlManager;

	// Adapters
	TodoListAdapater mAdapter;

	// Views
	CheckBox mTitleBox;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		mSqlManager = new SqlManager(getActivity());

		((MainActivity) getActivity()).hideFab();


		// pull our todolist from the parcelables
		mTodoList = getArguments().getParcelable(HomeFragment3.TAG_TODO_LISTS);

		String transitionName = getArguments().getString(HomeFragment3.TAG_TRANSITION_NAME);
		String relativeLayoutTransitionName = getArguments().getString(HomeFragment3.TAG_TRANSITION_RELATIVELAYOUT);
//		Log.d(TAG, relativeLayoutTransitionName);

		// inflate views
		View view = inflater.inflate(R.layout.fragment_todolist, container, false);
		view.setTransitionName(relativeLayoutTransitionName);

		mTitleBox = (CheckBox) view.findViewById(R.id.titleBox);
		mTitleBox.setOnClickListener(this);
		mTitleBox.setText(mTodoList.getTitle());
		mTitleBox.setTransitionName(transitionName);
		// set up recycler view
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
//		mAdapter = new TodoListAdapater(mTodoList.getTodoItems(), this);
//		recyclerView.setAdapter(mAdapter);
		RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
		recyclerView.setLayoutManager(layoutManager);
		return view;
	}

	@Override
	public void onStop() {
		super.onStop();
		mSqlManager.closeDb();
	}

	@Override
	public void addNewTodoItem() {
		Todo todo = new Todo("");
//		mTodoList.getTodoItems().add(todo);
		mAdapter.notifyDataSetChanged();
	}

	@Override
	public void onCompletedChanged(final Todo todo) {
/*		if (!todo.isCompleted()) {
			mTitleBox.setChecked(false);
			mTodoList.setCompleted(false);
		} else {
			boolean allChecked = true;
			for (Todo todoItem : mTodoList.getTodoItems()) {
				if (!todoItem.isCompleted()) {
					allChecked = false;
				}
			}
			mTitleBox.setChecked(allChecked);
			mTodoList.setCompleted(allChecked);
		}

		new Thread(new Runnable() {
			public void run() {
				mSqlManager.updateTodoListCompleted(mTodoList);
				mSqlManager.updateTodoItemCompleted(todo);
			}
		}).start();*/
	}

	@Override
	public void onDescriptionSet(Todo todo) {
		todo.setId(mSqlManager.createTodoListItem(todo, mTodoList.getId()));
	}

	@Override
	public void onClick(View view) {
		boolean isChecked = mTitleBox.isChecked();
//		ArrayList<Todo> todos = mTodoList.getTodoItems();
//		for (Todo todo : todos) {
//			todo.setCompleted(isChecked);
//		}
		mAdapter.notifyDataSetChanged();
	}
}
