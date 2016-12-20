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

		// make sure we don't load multiple fragments
		if (savedInstanceState == null) {
			mMainFragment = new MainFragment();
		}

		// Load main fragment
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager
				.beginTransaction()
				.add(R.id.placeHolder, mMainFragment, TAG_MAIN_FRAGMENT)
				.commit();
	}
}
