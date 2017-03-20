package com.example.healthcare;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

	@BindView(R.id.bhamshah_id_no)
	TextView bhamshahIdNo;

	@BindView(R.id.eligible_members)
	TextView eligibleMembers;

	@BindView(R.id.pregnancy_weeks)
	TextView pregnancyWeeks;

	@Override
	public void onCreate(Bundle savedInstanceState,
			PersistableBundle persistentState) {
		super.onCreate(savedInstanceState, persistentState);
		setContentView(R.layout.activity_login);

		ButterKnife.bind(LoginActivity.this);

		bhamshahIdNo.addTextChangedListener(
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
					}
				}
		);
	}
}
