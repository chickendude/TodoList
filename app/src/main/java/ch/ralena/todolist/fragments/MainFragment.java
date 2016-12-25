package ch.ralena.todolist.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ch.ralena.todolist.R;
import ch.ralena.todolist.adapters.MainAdapter;
import ch.ralena.todolist.objects.TodoList;
import ch.ralena.todolist.sql.SqlManager;

/**
 * Created by crater-windoze on 12/20/2016.
 */

public class MainFragment extends Fragment implements MainAdapter.OnDataChangedListener, MainAdapter.OnItemClickedListener {
	// constants
	private static final String TAG = MainFragment.class.getSimpleName();
	public static final String TAG_TODO_LISTS = "todo_lists";

	// member fields
	private ArrayList<TodoList> mTodoLists;
	MainAdapter mAdapter;
	SqlManager mSqlManager;


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		mSqlManager = new SqlManager(getContext());
		if (savedInstanceState == null) {
			mTodoLists = mSqlManager.getTodoLists();
		} else {
			mTodoLists = savedInstanceState.getParcelableArrayList(TAG_TODO_LISTS);
		}

		View view = inflater.inflate(R.layout.fragment_main, container, false);

		// set up RecyclerView and adapter
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
		mAdapter = new MainAdapter(mTodoLists, this, this);
		recyclerView.setAdapter(mAdapter);
		// prepare LayoutManager
		RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
		recyclerView.setLayoutManager(layoutManager);

		return view;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putParcelableArrayList(TAG_TODO_LISTS, mTodoLists);
		super.onSaveInstanceState(outState);
	}

	public void addTodoList(TodoList todoList) {
		long id = mSqlManager.createTodoList(todoList);
		todoList.setId(id);
		mTodoLists.add(todoList);
		mAdapter.updateTodoList(mTodoLists);
		mAdapter.notifyDataSetChanged();
	}

	@Override
	public void onDeleteClicked(TodoList todoList) {
		mSqlManager.deleteTodoList(todoList);
		mTodoLists.remove(todoList);
		mAdapter.updateTodoList(mTodoLists);
		mAdapter.notifyDataSetChanged();
	}

	@Override
	public void onTitleEdited(TodoList todoList) {
		mSqlManager.updateTodoListTitle(todoList);
	}

	@Override
	public void onCompletionStatusChanged(TodoList todoList) {
		mSqlManager.updateTodoListCompleted(todoList);
	}

	@Override
	public void onOpenTodoList(TodoList todoList) {
		TodoListFragment todoListFragment = new TodoListFragment();
		Bundle bundle = new Bundle();
		bundle.putParcelable(TAG_TODO_LISTS, todoList);
		todoListFragment.setArguments(bundle);
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.placeHolder, todoListFragment)
				.addToBackStack("HELLO")
				.commit();
	}
}
