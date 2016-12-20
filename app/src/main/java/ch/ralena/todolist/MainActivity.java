package ch.ralena.todolist;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ch.ralena.todolist.fragments.MainFragment;

// TODO: Add floating button
// TODO: Load main fragment

public class MainActivity extends AppCompatActivity {

	private static final String TAG_MAIN_FRAGMENT = "main_fragment";
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
}
