package com.mru.ptr.event.ui;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewTreeObserver;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mru.ptr.BackDisabledToolbarFragment;
import com.mru.ptr.MainActivity;
import com.mru.ptr.R;
import com.mru.ptr.Response;
import com.mru.ptr.event.ui.adapters.EventsAdapter;
import com.mru.ptr.event.ui.model.EventDataModel;
import com.mru.ptr.utils.RecyclerViewClickListener;
import java.util.ArrayList;
import java.util.List;


public class EventsFragment extends BackDisabledToolbarFragment implements RecyclerViewClickListener {

  private RecyclerView eventsRecyclerView;
  private LinearLayoutManager recyclerLayoutManager;
  private EventsAdapter eventsAdapter;
  private List<EventDataModel> events;
  private EventsViewModel eventsViewModel;
  private ProgressBar progressBar;
  private AppCompatTextView errorText;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    events = new ArrayList<>();
    eventsViewModel = ViewModelProviders.of(requireActivity()).get(EventsViewModel.class);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
    Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_slightly_padded_list, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    eventsRecyclerView = view.findViewById(R.id.list);
    progressBar = view.findViewById(R.id.loading_progress);
    errorText = view.findViewById(R.id.error_message_text);
    progressBar.setVisibility(View.VISIBLE);
    recyclerLayoutManager = new LinearLayoutManager(getContext());
    eventsAdapter = new EventsAdapter(events, this);

    eventsRecyclerView.setLayoutManager(recyclerLayoutManager);
    eventsRecyclerView.setAdapter(eventsAdapter);

    eventsViewModel.events.observe(getViewLifecycleOwner(),
      new Observer<List<EventDataModel>>() {
        @Override
        public void onChanged(List<EventDataModel> eventDataModels) {
          progressBar.setVisibility(View.GONE);
          if(eventDataModels == null) {
            errorText.setText("No Events");
          }
          else {
            eventsAdapter.setData(eventDataModels);
          }
        }
      });

  }


  @Override
  public void onItemSelected(View view, int position) {
    if(getActivity() != null) {
      eventsViewModel.selectEvent(eventsAdapter.getDataModelByPosition(position));
      ((MainActivity) getActivity()).showEventDetailPage(view);
    }
  }

  @Override
  public String getTitle() {
    return "Events";
  }
}
