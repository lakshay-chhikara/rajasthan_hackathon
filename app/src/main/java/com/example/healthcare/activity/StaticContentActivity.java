package com.example.healthcare.activity;

import com.example.healthcare.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by karangarg on 20/03/17.
 */

public class StaticContentActivity extends AppCompatActivity {

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_data);
        ButterKnife.bind(StaticContentActivity.this);
    }

    @OnClick(R.id.staticDataButton)
    public void GoToNextActivity(View v){
        Intent intent = new Intent(StaticContentActivity.this, TimelineActivity.class);
        startActivity(intent);
    }
}
