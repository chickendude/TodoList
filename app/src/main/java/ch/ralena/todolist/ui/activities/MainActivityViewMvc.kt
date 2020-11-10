package ch.ralena.todolist.ui.activities

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import ch.ralena.todolist.R
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

	init {
		fab.setOnClickListener {
			for (listener in listeners) {
				listener.onFabClick()
			}
		}
	}

}