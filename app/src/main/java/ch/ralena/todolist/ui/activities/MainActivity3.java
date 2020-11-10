package ch.ralena.todolist.ui.activities;

import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import ch.ralena.todolist.R;
import ch.ralena.todolist.data.models.TodoList;
import ch.ralena.todolist.ui.fragments.MainFragment;
import ch.ralena.todolist.ui.fragments.NewTodoListFragment;
import ch.ralena.todolist.ui.transitions.Scale;

// TODO: Check into automatic animations via layout
public class MainActivity3 extends BaseActivity implements NewTodoListFragment.SubmitNewTodoListListener {
	private static final String TAG = MainActivity3.class.getSimpleName();

	private static final String TAG_MAIN_FRAGMENT = "main_fragment";
	private static final String TAG_NEW_TODO_LIST = "new_todo_list";
	private MainFragment mMainFragment;
	private RelativeLayout mRelativeLayout;

	private static ImageView mFAB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mRelativeLayout = (RelativeLayout) findViewById(R.id.activity_main);
		mFAB = (ImageView) findViewById(R.id.fab);
		showFab();

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
		newTodoListFragment.show(getFragmentManager(), TAG_NEW_TODO_LIST);
	}

	private void animateFab(int visibility) {
		Transition fab = new Scale();
		fab.addTarget(mFAB);
		ViewGroup viewGroup = (ViewGroup) findViewById(R.id.placeHolder);
		TransitionManager.beginDelayedTransition(mRelativeLayout, fab);
		mFAB.setVisibility(visibility);
	}

	@Override
	public void onCancelNewTodoList() {
		showFab();
	}

	public void hideFab() {
		animateFab(View.GONE);
	}

	public void showFab() {
		animateFab(View.VISIBLE);
	}

	@Override
	public void onSubmittedNewTodoList(TodoList todoList) {
		mMainFragment.addTodoList(todoList);
	}
}
