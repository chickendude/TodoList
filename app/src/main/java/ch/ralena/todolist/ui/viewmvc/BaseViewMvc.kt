package ch.ralena.todolist.ui.viewmvc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes

open class BaseViewMvc<LISTENER>(
		private val layoutInflater: LayoutInflater,
		private val parent: ViewGroup?,
		@LayoutRes layoutId: Int
) {
	val rootView = layoutInflater.inflate(layoutId, parent, false)
//	protected val context by lazy { rootView.context }

	protected val listeners = HashSet<LISTENER>()

	fun registerListener(listener: LISTENER) {
		listeners.add(listener)
	}

	fun unregisterListener(listener: LISTENER) {
		listeners.remove(listener)
	}

	fun <T : View?> findViewById(@IdRes id: Int): T {
		return rootView.findViewById<T>(id)
	}
}