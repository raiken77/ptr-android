package com.mru.ptr;

import android.graphics.Rect;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.State;

/**
 * Created by Jonathan on 2019-10-17.
 */
public class MarginItemDecoration extends RecyclerView.ItemDecoration {

  private int top;
  private int left;
  private int right;
  private int bottom;

  public MarginItemDecoration(int top, int bottom, int left, int right) {
    this.top = top;
    this.bottom = bottom;
    this.left = left;
    this.right = right;
  }

  @Override
  public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull State state) {
    if(parent.getChildAdapterPosition(view) == 0 ){
      outRect.top = top;
    }

    outRect.left = left;
    outRect.right = right;
    outRect.bottom = bottom;

  }
}
