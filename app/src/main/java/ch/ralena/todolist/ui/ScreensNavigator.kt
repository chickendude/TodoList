package ch.ralena.todolist.ui

import androidx.fragment.app.FragmentManager
import ch.ralena.todolist.R
import ch.ralena.todolist.ui.fragments.MainFragment
import javax.inject.Inject

class ScreensNavigator @Inject constructor(
		private val fragmentManager: FragmentManager
) {
	fun toMainFragment() {
		fragmentManager
				.beginTransaction()
				.add(R.id.placeHolder, MainFragment())
				.commit()
	}
}
