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
import android.widget.SearchView;

import com.example.candidate_search_test_app.databinding.CandidateListFragmentBinding;
import com.example.candidate_search_test_app.model.CandidateList;
import com.example.candidate_search_test_app.model.Candidates;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Koshini Bulathsinhala
 */

public class CandidateListFragment extends Fragment {

    private CandidateListViewModel mViewModel;
    private RecyclerView recyclerView;
    private CandidateListFragmentBinding binding;
    private CandidateAdapter adapter;
    private SearchView searchView;

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
        searchView = binding.searchView;
        init();
    }

    /**
     * initiate necessary methods
     */
    private void init() {
        loadCandidates();
        searchByName();
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

    /**
     * search candidates by first name or last name
     */
    private void searchByName() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public boolean onQueryTextChange(String newText) {
                List<Candidates> list = new ArrayList<>();
                if (newText.length() >= 3) {
                    mViewModel.getLiveData().observe(getViewLifecycleOwner(), candidateList -> {
                        for (int i = 0; i < candidateList.getCandidates().size(); i++) {
                            if (candidateList.getCandidates().get(i).getName().getFirst()
                                    .toLowerCase(Locale.ROOT).contains(newText) ||
                                    candidateList.getCandidates().get(i).getName().getLast()
                                            .toLowerCase(Locale.ROOT).contains(newText)) {
                                Candidates candidates = new Candidates(
                                        candidateList.getCandidates().get(i).getGender(),
                                        candidateList.getCandidates().get(i).getEmail(),
                                        candidateList.getCandidates().get(i).getPhone(),
                                        candidateList.getCandidates().get(i).getCell(),
                                        candidateList.getCandidates().get(i).getNat(),
                                        candidateList.getCandidates().get(i).getName(),
                                        candidateList.getCandidates().get(i).getAddress(),
                                        candidateList.getCandidates().get(i).getDob(),
                                        candidateList.getCandidates().get(i).getPicture()
                                );
                                list.add(candidates);
                            } else {
                                adapter.notifyDataSetChanged();
                            }
                        }
                        CandidateList candidatesList = new CandidateList(list);
                        adapter = new CandidateAdapter();
                        adapter.setCandidates(candidatesList);
                        adapter.notifyDataSetChanged();
                        setDataToAdapter();
                    });
                } else {
                    loadCandidates();
                }
                return false;
            }
        });
    }
}