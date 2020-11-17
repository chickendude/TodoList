package ch.ralena.todolist.di.modules

import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(
		private val fragment: Fragment
) {

	@Provides
	fun fragment() = fragment
}