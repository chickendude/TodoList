package ch.ralena.todolist;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ch.ralena.todolist.fragments.MainFragment;
import ch.ralena.todolist.fragments.NewTodoListFragment;
import ch.ralena.todolist.objects.TodoList;

// TODO: Add floating button
// TODO: Load main fragment

public class MainActivity extends AppCompatActivity implements NewTodoListFragment.SubmitNewTodoListListener {

	private static final String TAG_MAIN_FRAGMENT = "main_fragment";
	private static final String TAG_NEW_TODO_LIST = "new_todo_list";
	private MainFragment mMainFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Load main fragment
		FragmentManager fragmentManager = getSupportFragmentManager();

		// make sure we don't load it multiple times
		MainFragment savedFragment = (MainFragment) fragmentManager
				.findFragmentByTag(TAG_MAIN_FRAGMENT);
		if (savedFragment == null) {
			mMainFragment = new MainFragment();
			fragmentManager
					.beginTransaction()
					.add(R.id.placeHolder, mMainFragment, TAG_MAIN_FRAGMENT)
					.commit();
		} else {
			mMainFragment = (MainFragment) fragmentManager.findFragmentByTag(TAG_MAIN_FRAGMENT);
		}
	}

	public void onFabClick(View view) {
		NewTodoListFragment newTodoListFragment = new NewTodoListFragment();
		newTodoListFragment.setStyle(DialogFragment.STYLE_NO_FRAME, 0);
		newTodoListFragment.show(getFragmentManager(), TAG_NEW_TODO_LIST);
	}

	@Override
	public void onSubmittedNewTodoList(TodoList todoList) {
		mMainFragment.addTodoList(todoList);
	}
}
