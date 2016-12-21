package ch.ralena.todolist.transitions;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by crater-windoze on 12/21/2016.
 */

public class Scale extends Visibility
{
	@Override
	public Animator onAppear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
		return createAnimation(view, 0, 1);
	}

	@Override
	public Animator onDisappear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
		return createAnimation(view, 1, 0);
	}

	private Animator createAnimation(View view, int start, int end) {
		AnimatorSet set = new AnimatorSet();
		ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, View.SCALE_X, start, end);
		ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, View.SCALE_Y, start, end);
		set.playTogether(scaleX, scaleY);
		return set;
	}
}
