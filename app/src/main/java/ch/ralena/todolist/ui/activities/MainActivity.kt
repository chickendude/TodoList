package ch.ralena.todolist.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import ch.ralena.todolist.ui.ScreensNavigator
import ch.ralena.todolist.ui.base.BaseActivity
import ch.ralena.todolist.ui.viewmvc.ViewMvcFactory
import javax.inject.Inject

class MainActivity : BaseActivity(), MainActivityViewMvc.Listener {
	companion object {
		const val TAG = "MainActivity2"
	}

	@Inject
	lateinit var viewMvcFactory: ViewMvcFactory

	@Inject
	lateinit var screensNavigator: ScreensNavigator

	private lateinit var viewMvc: MainActivityViewMvc

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		injector.inject(this)
		viewMvc = viewMvcFactory.newMainActivityViewMvc()
		setContentView(viewMvc.rootView)
		screensNavigator.toMainFragment()
	}

	override fun onStart() {
		super.onStart()
		viewMvc.registerListener(this)
	}

	override fun onStop() {
		super.onStop()
		viewMvc.unregisterListener(this)
	}

	fun hideFab() {
		viewMvc.animateFab(View.GONE)
	}

	fun showFab() {
		viewMvc.animateFab(View.VISIBLE)
	}


	override fun onFabClick() {
		Log.d(TAG, "fab clicked")
		hideFab()
		screensNavigator.toNewTodoListFragment()
	}
}