package com.example.candidate_search_test_app.candidates.CandidateList;

import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.candidate_search_test_app.databinding.CandidateListFragmentBinding;

/**
 * Created by Koshini Bulathsinhala
 */

public class CandidateListFragment extends Fragment {

    private CandidateListViewModel mViewModel;
    private RecyclerView recyclerView;
    private CandidateListFragmentBinding binding;
    private CandidateAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(CandidateListViewModel.class);
        binding = CandidateListFragmentBinding.inflate(inflater, container, false);

        View view = binding.getRoot();
        initComponents();
        return view;
    }

    /**
     * initiate components
     */
    private void initComponents() {
        recyclerView = binding.recyclerView;
        init();
    }

    /**
     * initiate necessary methods
     */
    private void init() {
        loadCandidates();
    }

    /**
     * load candidates details from viewmodel
     */
    @SuppressLint("NotifyDataSetChanged")
    private void loadCandidates() {
        mViewModel.getLiveData().observe(getViewLifecycleOwner(), candidates -> {
            adapter = new CandidateAdapter();
            adapter.setCandidates(candidates);
            adapter.notifyDataSetChanged();
            setDataToAdapter();
        });
    }

    /**
     * set loaded candidate details to adapter
     */
    private void setDataToAdapter() {
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(false);
    }
}