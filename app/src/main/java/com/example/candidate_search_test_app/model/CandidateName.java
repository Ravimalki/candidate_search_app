package com.example.candidate_search_test_app.model;

/**
 * Created by Koshini Bulathsinhala
 */
public class CandidateName {
    private String title, first, last;

    @Override
    public String toString() {
        return title + ". " + first + " " + last;
    }
}
