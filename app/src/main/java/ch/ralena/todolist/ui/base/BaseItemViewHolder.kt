package ch.ralena.todolist.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import ch.ralena.todolist.R
import ch.ralena.todolist.data.local.db.entities.TodoList

abstract class BaseItemViewHolder<T : Any, LISTENER>(
		@LayoutRes private val layoutId: Int,
		private val parent: ViewGroup
) : RecyclerView.ViewHolder(
		LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
) {
	protected val listeners = HashSet<LISTENER>()

	fun registerListener(listener: LISTENER) {
		listeners.add(listener)
	}

	fun unregisterListener(listener: LISTENER) {
		listeners.remove(listener)
	}

	abstract fun bind(data: T)
}