package com.example.candidate_search_test_app.candidates.SelectedList;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.candidate_search_test_app.databinding.CandidateContentListViewBinding;
import com.example.candidate_search_test_app.model.CandidateList;
import com.example.candidate_search_test_app.model.Candidates;
import com.example.candidate_search_test_app.model.SelectedCandidates;

import java.util.List;

/**
 * Created by Koshini Bulathsinhala
 */
public class SelectedListAdapter extends RecyclerView.Adapter<SelectedListViewHolder> {
    private List<SelectedCandidates> list;
    private SelectedCandidates candidates;

    public void setList(List<SelectedCandidates> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public SelectedListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CandidateContentListViewBinding binding = CandidateContentListViewBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new SelectedListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectedListViewHolder holder, int position) {
        candidates = list.get(position);
        Glide.with(holder.binding.getRoot()).load(candidates.getImage()).into(holder.binding.imageView);
        holder.binding.fullName.setText(candidates.getName());
        holder.binding.age.setText(candidates.getAge());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void removeItem (int position){
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(SelectedCandidates item,int position){
        list.add(position,item);
        notifyItemInserted(position);
    }

    public List<SelectedCandidates> getList() {
        return list;
    }
}
