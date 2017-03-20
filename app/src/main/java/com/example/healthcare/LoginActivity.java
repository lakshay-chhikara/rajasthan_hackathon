package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.healthcare.activity.StaticContentActivity;
import com.example.healthcare.services.BhamshahService;
import com.example.healthcare.services.HealthCareService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class LoginActivity extends AppCompatActivity {

	@BindView(R.id.bhamashah_id_no)
	EditText bhamashahIdNo;

	@BindView(R.id.eligible_members)
	Spinner eligibleMembers;

	@BindView(R.id.pregnancy_weeks)
	EditText pregnancyWeeks;

	private Retrofit bhamashahRetrofit = new Retrofit.Builder()
			.baseUrl("https://apitest.sewadwaar.rajasthan.gov.in")
			.addConverterFactory(ScalarsConverterFactory.create())
			.build();

	private Retrofit healthCareRetrofit = new Retrofit.Builder()
			.baseUrl("https://apitest.sewadwaar.rajasthan.gov.in")
			.addConverterFactory(ScalarsConverterFactory.create())
			.build();

	private ArrayAdapter<String> eligibleMembersAdapter;

	private String bhamashahId, dob, mId, pincode, address, name;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		ButterKnife.bind(LoginActivity.this);

		List<String> eligibleMembersList;
		if (savedInstanceState != null) {
			eligibleMembersList = new ArrayList<>();
		} else {
			eligibleMembersList = new ArrayList<>();
		}
		eligibleMembersAdapter = new ArrayAdapter<String>(
				LoginActivity.this,
				android.R.layout.simple_list_item_1,
				android.R.id.text1,
				eligibleMembersList);

		bhamashahIdNo.addTextChangedListener(
				new TextWatcher() {
					@Override
					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
					}

					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
					}

					@Override
					public void afterTextChanged(Editable s) {
						int bhamashahMaxLimit = getResources().getInteger(R.integer.bhamashah_max_limit);
						if (s.length() == bhamashahMaxLimit) {
							eligibleMembersAdapter.clear();
							final String bhamashahIdNo = s.toString();
							BhamshahService bhamashahService =
									bhamashahRetrofit.create(BhamshahService.class);
							bhamashahService.
									getInfo(bhamashahIdNo).enqueue(
									new Callback<String>() {
										@Override
										public void onResponse(Call<String> call, Response<String> response) {
											try {
												JSONObject resp = new JSONObject(response.body());
												JSONArray members = resp.getJSONArray("family_Details");
												for (int i = 0; i < members.length(); ++i) {
													JSONObject member = members.getJSONObject(i);
													String name = member.getString("NAME_ENG");
													eligibleMembersAdapter.add(name);
												}
											} catch (JSONException e) {
												e.printStackTrace();
											}
											eligibleMembers.setAdapter(eligibleMembersAdapter);
										}

										@Override
										public void onFailure(Call<String> call, Throwable t) {
											Snackbar.make(eligibleMembers,
													R.string.unable_to_fetch,
													Snackbar.LENGTH_SHORT)
												.show();
										}
									}
							);
						}
					}
				}
		);
	}

	@OnClick(R.id.register)
	public void register() {
		HealthCareService healthCareService =
				healthCareRetrofit.create(HealthCareService.class);
		healthCareService.register(bhamashahIdNo.getText().toString(),
				bhamashahId, dob, mId, pincode, address, name,
				Integer.valueOf(pregnancyWeeks.getText().toString())).enqueue(
			new Callback<String>() {
				@Override
				public void onResponse(Call<String> call, Response<String> response) {
					Intent staticContentActivity = new Intent(
							LoginActivity.this, StaticContentActivity.class);
					startActivity(staticContentActivity);
				}

				@Override
				public void onFailure(Call<String> call, Throwable t) {
				}
			});
	}
}
