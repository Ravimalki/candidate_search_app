package com.example.candidate_search_test_app.candidates.CandidateList;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.candidate_search_test_app.model.CandidateList;
import com.example.candidate_search_test_app.network.APIClient;
import com.example.candidate_search_test_app.network.Services;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Koshini Bulathsinhala
 */

public class CandidateListViewModel extends ViewModel {
    private final MutableLiveData<CandidateList> liveData;
    private Services services;

    public CandidateListViewModel() {
        liveData = new MutableLiveData<>();
        init();
    }

    public MutableLiveData<CandidateList> getLiveData() {
        return liveData;
    }

    private void init() {
        services = APIClient.getClient().create(Services.class);
        loadCandidates();
    }

    private void loadCandidates() {
        Call<CandidateList> call = services.getCandiates();
        call.enqueue(new Callback<CandidateList>() {
            @Override
            public void onResponse(@NonNull Call<CandidateList> call, @NonNull Response<CandidateList> response) {
                liveData.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<CandidateList> call, @NonNull Throwable t) {
                System.out.println(t.getMessage());

            }
        });
    }
}