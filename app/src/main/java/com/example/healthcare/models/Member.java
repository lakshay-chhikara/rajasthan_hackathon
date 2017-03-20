package com.example.healthcare.models;

import com.google.gson.annotations.SerializedName;

public class Member {
	public Member(String name) {
		this.name = name;
	}

	@SerializedName("GENDER")
	GENDER gender;

	@SerializedName("FAMILYIDNO")
	String bhamashahIdNo;

	@SerializedName("BHAMASHAH_ID")
	String bhamashahId;

	@SerializedName("DOB")
	String dob;

	@SerializedName("M_ID")
	String mId;

	@SerializedName("PIN_CODE")
	String pincode;

	@SerializedName("ADDRESS")
	String address;

	@SerializedName("NAME_ENG")
	String name;

	public GENDER getGender() {
		return gender;
	}

	public String getBhamashahIdNo() {
		return bhamashahIdNo;
	}

	public String getBhamashahId() {
		return bhamashahId;
	}

	public String getDob() {
		return dob;
	}

	public String getmId() {
		return mId;
	}

	public String getPincode() {
		return pincode;
	}

	public String getAddress() {
		return address;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

	public enum GENDER {
		@SerializedName("Male")
		MALE,

		@SerializedName("Female")
		FEMALE
	}
}
