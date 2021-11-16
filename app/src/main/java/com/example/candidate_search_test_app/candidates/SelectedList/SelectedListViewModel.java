package com.example.candidate_search_test_app.candidates.SelectedList;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.candidate_search_test_app.model.SelectedCandidates;
import com.example.candidate_search_test_app.sqliteFiles.SQLiteHelper;

import java.util.List;

/**
 * Created by Koshini Bulathsinhala
 */

public class SelectedListViewModel extends ViewModel {

    /**
     * load selected candidates from SQLite database
     *
     * @param context - context
     * @return - List of selected candidates
     */
    public List<SelectedCandidates> loadSelectedCandidates(Context context) {
        SQLiteHelper helper = new SQLiteHelper(context);

        return helper.fetchAllData();
    }
}