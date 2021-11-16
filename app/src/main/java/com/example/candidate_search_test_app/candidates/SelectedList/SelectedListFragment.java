package com.example.candidate_search_test_app.candidates.SelectedList;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.candidate_search_test_app.databinding.SelectedListFragmentBinding;
import com.example.candidate_search_test_app.model.SelectedCandidates;
import com.example.candidate_search_test_app.sqliteFiles.SQLiteHelper;
import com.google.android.material.snackbar.Snackbar;

/**
 * Created by Koshini Bulathsinhala
 */

public class SelectedListFragment extends Fragment {

    private SelectedListViewModel mViewModel;
    private SelectedListFragmentBinding binding;
    private SelectedListAdapter adapter;
    private RecyclerView recyclerView;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(SelectedListViewModel.class);
        binding = SelectedListFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        initComponents();
        return view;
    }

    public void initComponents() {
        recyclerView = binding.recyclerview;
        loadSelectedCandidates();
    }


    @SuppressLint("NotifyDataSetChanged")
    public void loadSelectedCandidates() {
        adapter = new SelectedListAdapter();
        adapter.setList(mViewModel.loadSelectedCandidates(getContext()));
        adapter.notifyDataSetChanged();
        setDataToAdapter();
    }

    public void setDataToAdapter() {
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(false);
        enableSwipeToDelete();
    }

    private void enableSwipeToDelete() {
        SwipeToDeleteCallBack swipeToDeleteCallBack = new SwipeToDeleteCallBack(getContext()) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();
                final SelectedCandidates item = adapter.getList().get(position);
                SQLiteHelper helper = new SQLiteHelper(getContext());

                new AlertDialog.Builder(getContext())
                        .setTitle("Deselect Candidate")
                        .setMessage("Are you sure you want to deselect?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            adapter.removeItem(position);
                            helper.deleteData(item.getId());

                            dialog.dismiss();

                            Snackbar snackbar = Snackbar.make(binding.getRoot(), "Item was removed from the list",
                                    Snackbar.LENGTH_LONG);
                            snackbar.setAction("UNDO", v -> {
                                helper.insertData(item.getImage(), item.getName(), item.getAge());
                                adapter.restoreItem(item, position);
                                recyclerView.scrollToPosition(position);
                            });
                            snackbar.setActionTextColor(Color.YELLOW);
                            snackbar.show();
                        })
                        .setNegativeButton("No", (dialog, which) -> {
                            adapter.notifyDataSetChanged();
                            dialog.dismiss();
                        }).show();
            }
        };
        ItemTouchHelper helper = new ItemTouchHelper(swipeToDeleteCallBack);
        helper.attachToRecyclerView(recyclerView);
    }
}