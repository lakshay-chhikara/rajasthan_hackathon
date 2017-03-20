package com.example.healthcare.services;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;

public interface HealthCareService {

	@GET("register")
	Call<String> register(@Field("bhamashahIdNo") String bhamashahIdNo,
			@Field("bhamashahId") String bhamashahId,
			@Field("dob") String dob,
			@Field("mid") String mId,
			@Field("pincode") String pincode,
			@Field("address") String address,
			@Field("name") String name,
			@Field("pregnancyWeeks") int pregnancyWeeks);
}
