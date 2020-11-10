package ch.ralena.todolist.ui.activities

import android.transition.Transition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import ch.ralena.todolist.R
import ch.ralena.todolist.ui.transitions.Scale
import ch.ralena.todolist.ui.viewmvc.BaseViewMvc

class MainActivityViewMvc(
		private val layoutInflater: LayoutInflater,
		private val parent: ViewGroup?
) : BaseViewMvc<MainActivityViewMvc.Listener>(
		layoutInflater,
		parent,
		R.layout.activity_main
) {
	interface Listener {
		fun onFabClick()
	}

	private val fab: ImageView = findViewById(R.id.fab)
	private val relativeLayout: RelativeLayout = findViewById(R.id.activity_main)

	init {
		fab.setOnClickListener {
			for (listener in listeners) {
				listener.onFabClick()
			}
		}
	}

	fun animateFab(visibility: Int) {
		val transition: Transition = Scale()
		transition.addTarget(fab)
		TransitionManager.beginDelayedTransition(relativeLayout, transition)
		fab.visibility = visibility
	}
}