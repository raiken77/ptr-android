package com.mru.ptr.settings.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.mru.ptr.R;
import com.mru.ptr.settings.ui.SettingsAdapter.SettingsViewHolder;
import com.mru.ptr.utils.RecyclerViewClickListener;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-17.
 */
public class SettingsAdapter extends RecyclerView.Adapter<SettingsViewHolder> {

  List<SettingsDataModel> settingsDataModels;
  RecyclerViewClickListener clickListener;

  public SettingsAdapter(List<SettingsDataModel> settingsDataModels,
    RecyclerViewClickListener clickListener) {
    this.settingsDataModels = settingsDataModels;
    this.clickListener = clickListener;
  }

  @NonNull
  @Override
  public SettingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.settings_row, parent, false);
    return new SettingsViewHolder(view, clickListener);
  }

  @Override
  public void onBindViewHolder(@NonNull SettingsViewHolder holder, int position) {
    SettingsDataModel dataModel = settingsDataModels.get(position);

    if(dataModel != null) {
      holder.bindData(dataModel);
    }
  }

  @Override
  public int getItemCount() {
    return this.settingsDataModels.size();
  }

  public class SettingsViewHolder extends ViewHolder implements View.OnClickListener {

    WeakReference<RecyclerViewClickListener> clickListenerWeakReference;
    AppCompatTextView settingsTitle;

    public SettingsViewHolder(@NonNull View itemView, RecyclerViewClickListener clickListener) {
      super(itemView);
      this.clickListenerWeakReference = new WeakReference<>(clickListener);
      settingsTitle = itemView.findViewById(R.id.setting_title);
      itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
      if(this.clickListenerWeakReference.get() != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
        this.clickListenerWeakReference.get().onItemSelected(view, getAdapterPosition());
      }
    }

    public void bindData(SettingsDataModel dataModel) {
      this.settingsTitle.setText(dataModel.title);
    }


  }
}
