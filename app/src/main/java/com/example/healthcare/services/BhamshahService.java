package com.example.healthcare.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BhamshahService {

	@GET("app/live/Service/hofAndMember/ForApp/{bhamashahIdNo}?" +
			"client_id=ad7288a4-7764-436d-a727-783a977f1fe1")
	Call<String> getInfo(@Path("bhamashahIdNo") String bhamashahIdNo);
}
