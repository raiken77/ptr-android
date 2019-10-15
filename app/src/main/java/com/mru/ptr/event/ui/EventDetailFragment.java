package com.mru.ptr.event.ui;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.mru.ptr.DisabledToolbarFragment;
import com.mru.ptr.R;
import com.mru.ptr.event.ui.model.EventSharedViewModel;
import com.mru.ptr.event.ui.model.EventDataModel;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Jonathan on 2019-10-09.
 */
public class EventDetailFragment extends DisabledToolbarFragment {

  private AppCompatImageButton backButton;
  private AppCompatImageView eventImage;
  private AppCompatTextView eventTitle;
  private AppCompatTextView eventDate;
  private AppCompatTextView eventTime;
  private AppCompatTextView eventDetails;

  private EventSharedViewModel viewModel;

  public static EventDetailFragment newInstance(EventDataModel eventDataModel) {

    Bundle args = new Bundle();

    EventDetailFragment fragment = new EventDetailFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
    @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_event_detail, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    eventDate = view.findViewById(R.id.event_date);
    eventTime = view.findViewById(R.id.event_time);
    eventTitle = view.findViewById(R.id.event_title);
    eventDetails = view.findViewById(R.id.event_description);
    eventDetails.setMovementMethod(new ScrollingMovementMethod());
    backButton = view.findViewById(R.id.back_button);
    backButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        getFragmentManager().popBackStack();
      }
    });

    EventsViewModel viewModel = ViewModelProviders.of(requireActivity()).get(EventsViewModel.class);

    viewModel.getSelected().observe(getViewLifecycleOwner(), new Observer<EventDataModel>() {
      @Override
      public void onChanged(EventDataModel eventDataModel) {
        eventTitle.setText(eventDataModel.title);
        eventDetails.setText(eventDataModel.description);
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(eventDataModel.dateTime * 1000L);
        String date = DateFormat.format("dd MMM yyyy", cal).toString();
        String time = DateFormat.format("h:mm a", cal).toString();
        eventTime.setText(String.format(Locale.ENGLISH, "@ %s", time));
        eventDate.setText(date);
      }
    });
  }

}
