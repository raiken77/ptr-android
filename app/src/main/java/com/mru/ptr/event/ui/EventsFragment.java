package com.mru.ptr.event.ui;


import android.os.Bundle;
import android.view.View.OnClickListener;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import com.mru.ptr.MainActivity;
import com.mru.ptr.R;
import com.mru.ptr.event.ui.adapters.EventsAdapter;
import com.mru.ptr.event.ui.model.EventRow;
import com.mru.ptr.utils.RecyclerViewClickListener;
import java.util.ArrayList;
import java.util.List;


public class EventsFragment extends Fragment implements RecyclerViewClickListener {

  private RecyclerView eventsRecyclerView;
  private LinearLayoutManager recyclerLayoutManager;
  private EventsAdapter eventsAdapter;
  private List<EventRow> events;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    events = new ArrayList<>();
    events.add(new EventRow().setMeetingDate("07 Jan ee").setMeetingTitle("Hello "));
    events.add(new EventRow().setMeetingDate("08 Jan ee").setMeetingTitle("Hello world"));
    events.add(new EventRow().setMeetingDate("09 Jan ee").setMeetingTitle("Hello Hi"));
    events.add(new EventRow().setMeetingDate("10 Jan ee").setMeetingTitle("Hello LL"));
    events.add(new EventRow().setMeetingDate("11 Jan ee").setMeetingTitle("Hello s"));
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
    Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_events, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    eventsRecyclerView = view.findViewById(R.id.events_list);
    recyclerLayoutManager = new LinearLayoutManager(getContext());
    eventsAdapter = new EventsAdapter(events, this);

    eventsRecyclerView.setLayoutManager(recyclerLayoutManager);
    eventsRecyclerView.setAdapter(eventsAdapter);
  }


  @Override
  public void onItemSelected(int position) {
    if(getActivity() != null && getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
      ((MainActivity) EventsFragment.this.getActivity()).showEventDetailPage();
    }
  }
}
