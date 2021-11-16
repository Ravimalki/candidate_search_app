package com.example.candidate_search_test_app.details;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;

import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;
import com.example.candidate_search_test_app.model.Candidates;
import com.example.candidate_search_test_app.model.SelectedCandidates;
import com.example.candidate_search_test_app.databinding.DetailViewBinding;
import com.example.candidate_search_test_app.sqliteFiles.SQLiteHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Koshini Bulathsinhala
 */

public class DetailViewModel extends ViewModel {
    private Candidates candidate;

    public void loadCandidateDetails(Candidates candidates, DetailViewBinding binding) {
        try {
            this.candidate = candidates;
            @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
            Date date = format.parse(candidate.getDob().getDob());

            Glide.with(binding.getRoot()).load(candidate.getPicture().getLarge()).into(binding.imageView2);
            binding.name.setText(candidate.getName().toString());
            binding.address.setText(candidate.getAddress().toString());

            assert date != null;
            binding.dob.setText(format.format(date));
            binding.contact.setText(candidate.getCell());
            binding.email.setText(candidate.getEmail());

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void selectCandidate(Context context) {
        SQLiteHelper helper = new SQLiteHelper(context);
        helper.insertData(
                candidate.getPicture().getMedium(),
                candidate.getName().toString(),
                candidate.getDob().getAge());
    }

}