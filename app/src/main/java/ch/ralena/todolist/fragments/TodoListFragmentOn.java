package ch.ralena.todolist.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.ArrayList;

import ch.ralena.todolist.R;
import ch.ralena.todolist.adapters.TodoListAdapater;
import ch.ralena.todolist.objects.Todo;
import ch.ralena.todolist.objects.TodoList;

/**
 * Created by crater-windoze on 12/26/2016.
 */

public class TodoListFragmentOn extends Fragment implements TodoListAdapater.OnTodoItemListener,
		View.OnClickListener {
	TodoList mTodoList;

	// Adapters
	TodoListAdapater mAdapter;

	// Views
	CheckBox mTitleBox;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// pull our todolist from the parcelables
		mTodoList = getArguments().getParcelable(MainFragment.TAG_TODO_LISTS);
		// inflate views
		View view = inflater.inflate(R.layout.fragment_todolist, container, false);
		mTitleBox = (CheckBox) view.findViewById(R.id.titleBox);
		mTitleBox.setOnClickListener(this);
		mTitleBox.setText(mTodoList.getTitle());
		// set up recycler view
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
		mAdapter = new TodoListAdapater(mTodoList.getTodoItems(), this);
		recyclerView.setAdapter(mAdapter);
		RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
		recyclerView.setLayoutManager(layoutManager);
		return view;
	}

	private void updateAdapterTodoList() {
		mAdapter.updateTodos(mTodoList.getTodoItems());
		mAdapter.notifyDataSetChanged();
	}

	@Override
	public void addNewTodoItem() {
		mTodoList.getTodoItems().add(new Todo(""));
		updateAdapterTodoList();
	}

	@Override
	public void onCompletedChanged(Todo todo) {
		if (!todo.isCompleted()) {
			mTitleBox.setChecked(false);
		} else {
			boolean allChecked = true;
			for(Todo todoItem : mTodoList.getTodoItems()) {
				if (!todoItem.isCompleted()) {
					allChecked = false;
				}
			}
			mTitleBox.setChecked(allChecked);
		}
	}

	@Override
	public void onClick(View view) {
		boolean isChecked = mTitleBox.isChecked();
		ArrayList<Todo> todos = mTodoList.getTodoItems();
		for (Todo todo : todos) {
			todo.setCompleted(isChecked);
		}
		updateAdapterTodoList();
	}
}
