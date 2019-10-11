package com.mru.ptr.manifesto.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.mru.ptr.R;
import com.mru.ptr.manifesto.ui.adapters.ManifestoAdapter.ManifestoViewHolder;
import com.mru.ptr.manifesto.ui.model.ManifestoRow;
import com.mru.ptr.utils.RecyclerViewClickListener;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-10.
 */
public class ManifestoAdapter extends RecyclerView.Adapter<ManifestoViewHolder> {

  private List<ManifestoRow> manifestoRows;
  private RecyclerViewClickListener clickListener;

  public ManifestoAdapter(List<ManifestoRow> manifestoRows, RecyclerViewClickListener clickListener) {
    this.manifestoRows = manifestoRows;
    this.clickListener = clickListener;
  }

  @NonNull
  @Override
  public ManifestoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.manifesto_row, parent, false);

    return new ManifestoViewHolder(view, this.clickListener);
  }

  @Override
  public void onBindViewHolder(@NonNull ManifestoViewHolder holder, int position) {
    ManifestoRow row = this.manifestoRows.get(position);

    if(row != null) {
      holder.bindData(row);
    }
  }

  @Override
  public int getItemCount() {
    return manifestoRows.size();
  }

  public static class ManifestoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private WeakReference<RecyclerViewClickListener> clickListenerWeakReference;
    private AppCompatImageView manifestoImageView;
    private AppCompatTextView manifestoTitle;

    public ManifestoViewHolder(@NonNull View itemView, RecyclerViewClickListener clickListener) {
      super(itemView);
      manifestoImageView = itemView.findViewById(R.id.manifesto_image);
      manifestoTitle = itemView.findViewById(R.id.manifesto_title);
      clickListenerWeakReference = new WeakReference<>(clickListener);
      itemView.setOnClickListener(this);
    }

    public void bindData(ManifestoRow data) {
      manifestoTitle.setText(data.manifestoTitle);
    }

    @Override
    public void onClick(View view) {
      if(clickListenerWeakReference.get() != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
        clickListenerWeakReference.get().onItemSelected(getAdapterPosition());
      }
    }
  }
}
