package com.mru.ptr.event.ui.adapters;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.mru.ptr.R;
import com.mru.ptr.event.ui.adapters.EventsAdapter.EventViewHolder;
import com.mru.ptr.event.ui.model.EventDataModel;
import com.mru.ptr.utils.RecyclerViewClickListener;
import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * Created by Jonathan on 2019-10-10.
 */
public class EventsAdapter extends RecyclerView.Adapter<EventViewHolder> {

  private List<EventDataModel> events;
  private RecyclerViewClickListener clickListener;

  public EventsAdapter(List<EventDataModel> events, RecyclerViewClickListener clickListener) {
    this.events = events;
    this.clickListener = clickListener;
  }

  public EventDataModel getDataModelByPosition(int position) {
    if(position < 0 || position > events.size() - 1) {
      return null;
    }

    return events.get(position);

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
    EventDataModel row = events.get(position);

    if(row != null) {
      String transitionName = row.title + ' ' + new Random().nextInt(40);
      ViewCompat.setTransitionName(holder.card, transitionName);
      holder.bindData(row);
    }
  }

  public void setData(List<EventDataModel> events) {
    this.events  = events;
    notifyDataSetChanged();
  }

  @Override
  public int getItemCount() {
    return events.size();
  }

  public static class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private WeakReference<RecyclerViewClickListener> weakClickListener;
    private AppCompatTextView meetingTitle;
    private AppCompatTextView meetingDate;
    private CardView card;
     AppCompatImageView meetingImage;


    public EventViewHolder(@NonNull View itemView, RecyclerViewClickListener clickListener) {
      super(itemView);
      this.meetingTitle = itemView.findViewById(R.id.event_item_title);
      this.meetingDate  = itemView.findViewById(R.id.event_item_date);
      this.meetingImage = itemView.findViewById(R.id.event_item_image);
      this.card = itemView.findViewById(R.id.card_container);
      this.weakClickListener = new WeakReference<>(clickListener);
      itemView.setOnClickListener(this);
    }

    public void bindData(EventDataModel eventData) {
      Calendar cal = Calendar.getInstance(Locale.ENGLISH);
      cal.setTimeInMillis(eventData.dateTime * 1000L);
      String date = DateFormat.format("dd MMM yyyy", cal).toString();
      meetingTitle.setText(eventData.title);
      meetingDate.setText(date);
    }

    @Override
    public void onClick(View view) {
      if(weakClickListener.get() != null) {
        if(getAdapterPosition() != RecyclerView.NO_POSITION) {
          weakClickListener.get().onItemSelected(this.card, getAdapterPosition());
        }
      }
    }
  }

}
