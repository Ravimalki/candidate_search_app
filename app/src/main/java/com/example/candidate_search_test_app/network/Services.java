package com.example.candidate_search_test_app.network;

import com.example.candidate_search_test_app.model.CandidateList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Koshini Bulathsinhala
 */
public interface Services {
    @GET("?results=50&format=pretty")
    Call<CandidateList> getCandiates();

}
