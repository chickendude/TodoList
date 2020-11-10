package ch.ralena.todolist.ui.viewmvc

import android.view.LayoutInflater
import ch.ralena.todolist.ui.activities.MainActivityViewMvc
import javax.inject.Inject

open class ViewMvcFactory @Inject constructor(
		private val layoutInflater: LayoutInflater,
) {
	fun newMainActivityViewMvc() = MainActivityViewMvc(layoutInflater, null)
}