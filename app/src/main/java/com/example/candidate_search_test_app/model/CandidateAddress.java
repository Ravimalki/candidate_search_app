package com.example.candidate_search_test_app.model;

import androidx.annotation.NonNull;

/**
 * Created by Koshini Bulathsinhala
 */
public class CandidateAddress {

    private Street street;
    private String city, state, country, postcode;


    @NonNull
    @Override
    public String toString() {
        return street.getNumber() + " " + street.getName() + "\n"
                + city + " " + state + "\n"
                + country + "\n" + postcode;
    }

    public static class Street {
        private String number, name;

        public String getNumber() {
            return number;
        }

        public String getName() {
            return name;
        }
    }
}
