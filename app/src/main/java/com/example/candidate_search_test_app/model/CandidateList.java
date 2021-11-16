package com.example.candidate_search_test_app.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Koshini Bulathsinhala
 */
public class CandidateList {
    @SerializedName("results")
    private List<Candidates> candidates;

    public CandidateList(List<Candidates> candidates) {
        this.candidates = candidates;
    }

    public List<Candidates> getCandidates() {
        return candidates;
    }
}
