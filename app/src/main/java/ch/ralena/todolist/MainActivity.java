package ch.ralena.todolist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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

// TODO: Check into automatic animations via layout
public class MainActivity extends AppCompatActivity implements NewTodoListFragment.SubmitNewTodoListListener {

	private static final String TAG_MAIN_FRAGMENT = "main_fragment";
	private static final String TAG_NEW_TODO_LIST = "new_todo_list";
	private MainFragment mMainFragment;

	private FloatingActionButton mFAB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mFAB = (FloatingActionButton) findViewById(R.id.fab);

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
		hideFab();
		NewTodoListFragment newTodoListFragment = new NewTodoListFragment();
		newTodoListFragment.setStyle(DialogFragment.STYLE_NO_FRAME, 0);
		newTodoListFragment.show(getSupportFragmentManager(), TAG_NEW_TODO_LIST);
	}

	private void animateFab(int visibility) {
		Transition fab = new Scale();
		fab.addTarget(mFAB);
		ViewGroup viewGroup = (ViewGroup) findViewById(R.id.placeHolder);
		TransitionManager.beginDelayedTransition(viewGroup, fab);
		mFAB.setVisibility(visibility);
	}

	@Override
	public void onCancelNewTodoList() {
		showFab();
	}

	public void hideFab() {
		animateFab(View.INVISIBLE);
	}

	public void showFab() {
		animateFab(View.VISIBLE);
	}

	@Override
	public void onSubmittedNewTodoList(TodoList todoList) {
		mMainFragment.addTodoList(todoList);
	}
}
