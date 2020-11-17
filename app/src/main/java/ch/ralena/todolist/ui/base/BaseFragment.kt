package ch.ralena.todolist.ui.base

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ch.ralena.todolist.MyApplication
import ch.ralena.todolist.di.modules.ActivityModule
import ch.ralena.todolist.di.modules.FragmentModule
import ch.ralena.todolist.di.modules.PresentationModule

open class BaseFragment : Fragment() {
	private val activityComponent get() = (requireActivity() as BaseActivity).activityComponent

	private val fragmentComponent by lazy {
		activityComponent.newFragmentComponent(
				FragmentModule(this)
		)
	}

	private val presentationComponent by lazy {
		fragmentComponent.newPresentationComponent(PresentationModule())
	}

	val injector get() = presentationComponent
}