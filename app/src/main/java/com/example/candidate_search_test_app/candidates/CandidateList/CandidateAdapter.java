package com.example.candidate_search_test_app.candidates.CandidateList;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.candidate_search_test_app.model.CandidateList;
import com.example.candidate_search_test_app.model.Candidates;
import com.example.candidate_search_test_app.R;
import com.example.candidate_search_test_app.databinding.CandidateContentListViewBinding;

/**
 * Created by Koshini Bulathsinhala.
 */
public class CandidateAdapter extends RecyclerView.Adapter<CandidateViewHolder> {
    private CandidateList candidates;

    public void setCandidates(CandidateList candidates) {
        this.candidates = candidates;
    }

    @NonNull
    @Override
    public CandidateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CandidateContentListViewBinding binding = CandidateContentListViewBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new CandidateViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CandidateViewHolder holder, int position) {
        Candidates list = candidates.getCandidates().get(position);

        /* set image url to glide */
        Glide.with(holder.binding.getRoot()).load(list.getPicture().getMedium())
                .into(holder.binding.imageView);

        holder.binding.fullName.setText(list.getName().toString());
        holder.binding.age.setText(list.getDob().getAge()+" yrs");

        /* Navigate to Detail view screen */
        holder.binding.cardView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("candidate", candidates.getCandidates().get(position));
            Navigation.findNavController(holder.binding.getRoot())
                    .navigate(R.id.action_candidateFragment_to_detailViewFragment, bundle);

        });
    }

    @Override
    public int getItemCount() {
        return candidates.getCandidates().size();
    }
}
