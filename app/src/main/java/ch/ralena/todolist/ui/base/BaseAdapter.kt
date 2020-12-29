package ch.ralena.todolist.ui.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T : Any, LISTENER, VH : BaseItemViewHolder<T, LISTENER>>(
		private val dataList: ArrayList<T>
) : RecyclerView.Adapter<VH>() {

	override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(dataList[position])

	override fun getItemCount(): Int {
		return dataList.size
	}

	fun updateData(data: List<T>) {
		dataList.clear()
		dataList.addAll(data)
		notifyDataSetChanged()
	}
}