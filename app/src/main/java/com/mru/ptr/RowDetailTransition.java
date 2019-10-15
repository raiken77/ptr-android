package com.mru.ptr;

import androidx.transition.ChangeBounds;
import androidx.transition.ChangeImageTransform;
import androidx.transition.ChangeTransform;
import androidx.transition.TransitionSet;

/**
 * Created by Jonathan on 2019-10-17.
 */
public class RowDetailTransition extends TransitionSet {

  public RowDetailTransition() {
    setOrdering(ORDERING_TOGETHER);
    setDuration(350);
    addTransition(new ChangeTransform()).
      addTransition(new ChangeBounds());

  }
}
