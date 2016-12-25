package ch.ralena.todolist.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ch.ralena.todolist.R;
import ch.ralena.todolist.objects.TodoList;

/**
 * Created by crater-windoze on 12/26/2016.
 */

public class TodoListFragment extends Fragment {
	TodoList mTodoList;

	//Views
	TextView mTitleLabel;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		mTodoList = getArguments().getParcelable(MainFragment.TAG_TODO_LISTS);
		View view = inflater.inflate(R.layout.fragment_todolist, container, false);
		mTitleLabel = (TextView) view.findViewById(R.id.titleLabel);
		mTitleLabel.setText(mTodoList.getTitle());
		return view;
	}
}
