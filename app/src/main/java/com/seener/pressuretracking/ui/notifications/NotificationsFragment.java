package com.seener.pressuretracking.ui.notifications;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.seener.pressuretracking.databinding.FragmentNotificationsBinding;
import com.seener.pressuretracking.model.StrategyInfos;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final RecyclerView recyclerView = binding.notifiRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        List<String> dataList = new ArrayList<>();
        dataList.add(StrategyInfos.Type.DOWN_UP_STAIRS.name());
        dataList.add(StrategyInfos.Type.DRIVING.name());
        dataList.add(StrategyInfos.Type.FLOOR_ESTIMATION.name());
        dataList.add(StrategyInfos.Type.OUTDOOR_INDOOR.name());
        StrategyAdapter adapter = new StrategyAdapter(dataList);
        adapter.setOnItemClickListener(position -> {
            //TODO
//            Toast.makeText(this.getContext(), dataList.get(position), Toast.LENGTH_SHORT).show();
            showAlertDialog(this.getContext(), dataList.get(position), StrategyInfos.Type.valueOf(dataList.get(position)).getInfo());
        });
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(), LinearLayout.VERTICAL));
        notificationsViewModel.getList().observe(getViewLifecycleOwner(), adapter::updateList);

        return root;
    }

    private void showAlertDialog(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
//                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}