package ch.ralena.todolist;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import ch.ralena.todolist.fragments.MainFragment;
import ch.ralena.todolist.fragments.NewTodoListFragment;
import ch.ralena.todolist.objects.TodoList;
import ch.ralena.todolist.transitions.Scale;

// TODO: Check into automatic animations via layout
public class MainActivity extends Activity implements NewTodoListFragment.SubmitNewTodoListListener {
	private static final String TAG = MainActivity.class.getSimpleName();

	private static final String TAG_MAIN_FRAGMENT = "main_fragment";
	private static final String TAG_NEW_TODO_LIST = "new_todo_list";
	private MainFragment mMainFragment;

	private static ImageView mFAB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mFAB = (ImageView) findViewById(R.id.fab);
		showFab();

		// Load main fragment
		FragmentManager fragmentManager = getFragmentManager();

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
		newTodoListFragment.show(getFragmentManager(), TAG_NEW_TODO_LIST);
	}

	private void animateFab(int visibility) {
		Log.d(TAG, "" + visibility);
		Transition fab = new Scale();
		fab.addTarget(mFAB);
		ViewGroup viewGroup = (ViewGroup) findViewById(R.id.placeHolder);
		TransitionManager.beginDelayedTransition(viewGroup, fab);
		mFAB.setVisibility(visibility);
		Log.d(TAG, "" + mFAB.getVisibility());
	}

	@Override
	public void onCancelNewTodoList() {
		showFab();
	}

	public void hideFab() {
		Log.d(TAG, "Hide FAB");
		animateFab(View.GONE);
	}

	public void showFab() {
		Log.d(TAG, "Show FAB");
		animateFab(View.VISIBLE);
	}

	@Override
	public void onSubmittedNewTodoList(TodoList todoList) {
		mMainFragment.addTodoList(todoList);
	}
}
