package ch.ralena.todolist;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;

import ch.ralena.todolist.fragments.MainFragment;
import ch.ralena.todolist.fragments.NewTodoListFragment;
import ch.ralena.todolist.objects.TodoList;
import ch.ralena.todolist.transitions.Scale;

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
		animateFab(view, View.INVISIBLE);
		NewTodoListFragment newTodoListFragment = new NewTodoListFragment();
		newTodoListFragment.setStyle(DialogFragment.STYLE_NO_FRAME, 0);
		newTodoListFragment.show(getSupportFragmentManager(), TAG_NEW_TODO_LIST);
	}

	private void animateFab(View view, int visibility) {
		Transition fab = new Scale();
		fab.addTarget(view);
		ViewGroup viewGroup = (ViewGroup) findViewById(R.id.placeHolder);
		TransitionManager.beginDelayedTransition(viewGroup, fab);
		view.setVisibility(visibility);
	}

	@Override
	public void onCancelNewTodoList() {
		animateFab(findViewById(R.id.fab), View.VISIBLE);
	}

	@Override
	public void onSubmittedNewTodoList(TodoList todoList) {
		mMainFragment.addTodoList(todoList);
	}
}
