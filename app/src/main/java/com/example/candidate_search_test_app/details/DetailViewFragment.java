package com.example.candidate_search_test_app.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.candidate_search_test_app.R;
import com.example.candidate_search_test_app.databinding.DetailViewBinding;
import com.example.candidate_search_test_app.model.Candidates;
import com.example.candidate_search_test_app.model.SelectedCandidates;
import com.example.candidate_search_test_app.sqliteFiles.SQLiteHelper;

import java.util.List;

/**
 * Created by Koshini Bulathsinhala
 */

public class DetailViewFragment extends Fragment {

    private DetailViewBinding binding;
    private DetailViewModel viewModel;
    private TextView textView;
    private Candidates candidates;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        binding = DetailViewBinding.inflate(inflater, container, false);

        initComponents();
        return binding.getRoot();
    }

    public void initComponents() {
        textView = getActivity().findViewById(R.id.selectbtn);
        candidates = getArguments().getParcelable("candidate");
        textView.setVisibility(View.VISIBLE);

        textView.setOnClickListener(v -> {
            textView.setText(R.string.selected);
            viewModel.selectCandidate(getContext());
        });
        setSelectBtn();
        loadCandidateDetails();
    }

    private void setSelectBtn() {
        SQLiteHelper helper = new SQLiteHelper(getContext());
        List<SelectedCandidates> selectedCandidates = helper.fetchAllData();

        for (int i = 0; i < selectedCandidates.size(); i++) {
            System.out.println("*" + selectedCandidates.get(i).getName() + "*" + " " + "*" + candidates.getName().toString() + "*");
            System.out.println("*" + selectedCandidates.get(i).getAge() + "*" + " " + "*" + candidates.getDob().getAge() + "*");

            if (selectedCandidates.get(i).getName().equals(candidates.getName().toString())
                    && selectedCandidates.get(i).getAge().equals(candidates.getDob().getAge())) {
                textView.setText(R.string.selected);
            } else {
                textView.setText(R.string.select);
            }
        }
    }

    private void loadCandidateDetails() {
        assert getArguments() != null;
        viewModel.loadCandidateDetails(candidates, binding);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        textView.setVisibility(View.INVISIBLE);
    }
}