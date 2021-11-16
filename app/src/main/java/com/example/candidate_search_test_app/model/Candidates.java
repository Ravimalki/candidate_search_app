package com.example.candidate_search_test_app.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Koshini Bulathsinhala
 */
public class Candidates implements Parcelable {
    private String gender, email, phone, cell, nat;
    private CandidateName name;
    private CandidateAddress location;
    private CandidateDOB dob;
    private CandidateProfileImg picture;

    public Candidates(
            String gender,
            String email,
            String phone,
            String cell,
            String nat,
            CandidateName name,
            CandidateAddress location,
            CandidateDOB dob,
            CandidateProfileImg picture) {
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.cell = cell;
        this.nat = nat;
        this.name = name;
        this.location = location;
        this.dob = dob;
        this.picture = picture;
    }

    protected Candidates(Parcel in) {
        gender = in.readString();
        email = in.readString();
        phone = in.readString();
        cell = in.readString();
        nat = in.readString();
    }

    public static final Creator<Candidates> CREATOR = new Creator<Candidates>() {
        @Override
        public Candidates createFromParcel(Parcel in) {
            return new Candidates(in);
        }

        @Override
        public Candidates[] newArray(int size) {
            return new Candidates[size];
        }
    };

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getCell() {
        return cell;
    }

    public String getNat() {
        return nat;
    }

    public CandidateName getName() {
        return name;
    }

    public CandidateAddress getAddress() {
        return location;
    }

    public CandidateDOB getDob() {
        return dob;
    }

    public CandidateProfileImg getPicture() {
        return picture;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(gender);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(cell);
        dest.writeString(nat);
    }
}
