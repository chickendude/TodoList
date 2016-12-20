package ch.ralena.todolist.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ch.ralena.todolist.R;
import ch.ralena.todolist.adapters.MainAdapter;
import ch.ralena.todolist.objects.TodoList;

/**
 * Created by crater-windoze on 12/20/2016.
 */

public class MainFragment extends Fragment {
	private List<TodoList> mTodoLists;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		mTodoLists = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			mTodoLists.add(new TodoList("Task " + i));
		}

		View view = inflater.inflate(R.layout.fragment_main, container, false);

		// set up RecyclerView and adapter
		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
		MainAdapter adapter = new MainAdapter(mTodoLists);
		recyclerView.setAdapter(adapter);
		// prepare LayoutManager
		RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
		recyclerView.setLayoutManager(layoutManager);

		return view;
	}
}
