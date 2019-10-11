package com.mru.ptr.event.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.mru.ptr.R;
import com.mru.ptr.event.ui.adapters.EventsAdapter.EventViewHolder;
import com.mru.ptr.event.ui.model.EventRow;
import com.mru.ptr.utils.RecyclerViewClickListener;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-10.
 */
public class EventsAdapter extends RecyclerView.Adapter<EventViewHolder> {

  private List<EventRow> events;
  private RecyclerViewClickListener clickListener;

  public EventsAdapter(List<EventRow> events, RecyclerViewClickListener clickListener) {
    this.events = events;
    this.clickListener = clickListener;
  }

  @NonNull
  @Override
  public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.event_row, parent, false);

    return new EventViewHolder(view, this.clickListener);
  }

  @Override
  public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
    EventRow row = events.get(position);

    if(row != null) {
      holder.bindData(row);
    }
  }

  @Override
  public int getItemCount() {
    return events.size();
  }

  public static class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private WeakReference<RecyclerViewClickListener> weakClickListener;
    private AppCompatTextView meetingTitle;
    private AppCompatTextView meetingDate;


    public EventViewHolder(@NonNull View itemView, RecyclerViewClickListener clickListener) {
      super(itemView);
      this.meetingTitle = itemView.findViewById(R.id.event_item_title);
      this.meetingDate  = itemView.findViewById(R.id.event_item_date);
      this.weakClickListener = new WeakReference<>(clickListener);
      itemView.setOnClickListener(this);
    }

    public void bindData(EventRow eventData) {
      meetingTitle.setText(eventData.meetingTitle);
      meetingDate.setText(eventData.meetingDate);
    }

    @Override
    public void onClick(View view) {
      if(weakClickListener.get() != null) {
        if(getAdapterPosition() != RecyclerView.NO_POSITION) {
          weakClickListener.get().onItemSelected(getAdapterPosition());
        }
      }
    }
  }

}
