package com.example.candidate_search_test_app.candidates.CandidateList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.candidate_search_test_app.model.CandidateList;
import com.example.candidate_search_test_app.model.Candidates;
import com.example.candidate_search_test_app.network.APIClient;
import com.example.candidate_search_test_app.network.Services;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Koshini Bulathsinhala
 */

public class CandidateListViewModel extends ViewModel {
    private MutableLiveData<CandidateList> liveData;
    private List<Candidates> candidatesList;
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
            public void onResponse(Call<CandidateList> call, Response<CandidateList> response) {
                liveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<CandidateList> call, Throwable t) {
                System.out.println(t.getMessage());

            }
        });
    }
}