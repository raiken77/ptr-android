package com.mru.ptr.settings.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ItemDecoration;
import com.mru.ptr.BackDisabledToolbarFragment;
import com.mru.ptr.MarginItemDecoration;
import com.mru.ptr.R;
import com.mru.ptr.utils.RecyclerViewClickListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan on 2019-10-09.
 */
public class SettingsFragment extends BackDisabledToolbarFragment implements RecyclerViewClickListener {

  SettingsAdapter settingsAdapter;
  RecyclerView settingsList;
  List<SettingsDataModel> settingsDataModelList;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    settingsDataModelList = new ArrayList<>();
    settingsDataModelList.add(new SettingsDataModel().setTitle("History"));
    settingsDataModelList.add(new SettingsDataModel().setTitle("Terms and condition"));
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
    @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_listview, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    settingsList = view.findViewById(R.id.list);
    settingsAdapter = new SettingsAdapter(settingsDataModelList, this);
    LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(getContext());
    ItemDecoration marginDecoration = new MarginItemDecoration(15, 15, 0, 0);

    settingsList.addItemDecoration(marginDecoration);
    settingsList.setLayoutManager(verticalLayoutManager);
    settingsList.setAdapter(settingsAdapter);
  }

  @Override
  public void onItemSelected(View view, int position) {

  }

  @Override
  public String getTitle() {
    return "More";
  }
}
