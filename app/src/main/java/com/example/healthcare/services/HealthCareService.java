package com.example.healthcare.services;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface HealthCareService {

	@POST("login.php")
	@FormUrlEncoded
	Call<String> register(@Field("bhamashahIdNo") String bhamashahIdNo,
			@Field("bhamashahId") String bhamashahId,
			@Field("dob") String dob,
			@Field("mid") String mId,
			@Field("pincode") String pincode,
			@Field("address") String address,
			@Field("name") String name,
			@Field("pregnancyWeeks") int pregnancyWeeks);

	@GET("timeline.php")
	Call<JsonArray> getTimeLine();

}
