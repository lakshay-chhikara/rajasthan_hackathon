package com.example.healthcare.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BhamashahDetails {

	@SerializedName("family_Details")
	List<Member> members;

	public List<Member> getMembers() {
		return members;
	}
}
