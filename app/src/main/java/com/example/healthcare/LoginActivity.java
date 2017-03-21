package com.example.healthcare;

import java.util.ArrayList;
import java.util.List;

import com.example.healthcare.activity.StaticContentActivity;
import com.example.healthcare.models.BhamashahDetails;
import com.example.healthcare.models.Member;
import com.example.healthcare.services.BhamashahService;
import com.example.healthcare.services.HealthCareService;
import com.google.gson.Gson;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
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

	private Retrofit healthCareRetrofit;

	private ArrayAdapter<Member> eligibleMembersAdapter;

	private Member member;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		ButterKnife.bind(LoginActivity.this);

		healthCareRetrofit = new Retrofit.Builder()
			.baseUrl(getResources().getString(R.string.BASE_URL))
			.addConverterFactory(ScalarsConverterFactory.create())
			.build();

		final List<Member> eligibleMembersList;
		if (savedInstanceState != null) {
			eligibleMembersList = new ArrayList<>();
			eligibleMembersList.add(new Member("Select mother"));
		} else {
			eligibleMembersList = new ArrayList<>();
			eligibleMembersList.add(new Member("Select mother"));
		}
		eligibleMembersAdapter = new ArrayAdapter<Member>(
				LoginActivity.this,
				android.R.layout.simple_list_item_1,
				android.R.id.text1,
				eligibleMembersList);
		eligibleMembers.setOnItemSelectedListener(
				new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view,
							int position, long id) {
						member = (Member) parent.getItemAtPosition(position);
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
					}
				}
		);
		eligibleMembers.setAdapter(eligibleMembersAdapter);

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
							eligibleMembersAdapter.add(new Member("Select mother"));
							final String bhamashahIdNo = s.toString();
							BhamashahService bhamashahService =
									bhamashahRetrofit.create(BhamashahService.class);
							bhamashahService.
									getInfo(bhamashahIdNo).enqueue(
									new Callback<String>() {
										@Override
										public void onResponse(Call<String> call, Response<String> response) {
											Gson gson = new Gson();
											BhamashahDetails resp = gson.fromJson(response.body(), BhamashahDetails.class);
											List<Member> members = resp.getMembers();
											for (int i = 0; i < members.size(); ++i) {
												Member member = members.get(i);
												if (member.getGender() == Member.GENDER.FEMALE) {
													eligibleMembersAdapter.add(member);
												}
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
		healthCareService.register(member.getBhamashahIdNo(),
				member.getBhamashahId(),
				member.getDob(),
				member.getmId(),
				member.getPincode(),
				member.getAddress(),
				member.getName(),
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
					Toast.makeText(LoginActivity.this, getString(R.string.unable_to_fetch), Toast.LENGTH_SHORT).show();
				}
			});
	}
}
