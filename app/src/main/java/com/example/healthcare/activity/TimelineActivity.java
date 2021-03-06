package com.example.healthcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.healthcare.R;
import com.example.healthcare.adapter.TimelineRecyclerViewAdapter;
import com.example.healthcare.entity.TimelineDO;
import com.example.healthcare.services.HealthCareService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by karangarg on 20/03/17.
 */

public class TimelineActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewTimeLine);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(TimelineActivity.this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        Retrofit healthCareRetrofit = new Retrofit.Builder()
            .baseUrl(TimelineActivity.this.getResources().getString(R.string.BASE_URL))
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        HealthCareService healthCareService =
            healthCareRetrofit.create(HealthCareService.class);
        healthCareService.getTimeLine().enqueue(
            new Callback<JsonArray>() {
                @Override
                public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                    Gson gson = new Gson();
                    List<TimelineDO> timelineDOList = Arrays.asList(gson.fromJson(response.body(), TimelineDO[].class));
                    mAdapter = new TimelineRecyclerViewAdapter(TimelineActivity.this, timelineDOList);
                    mRecyclerView.setAdapter(mAdapter);
                }

                @Override
                public void onFailure(Call<JsonArray> call, Throwable t) {
                    Toast.makeText(TimelineActivity.this, getString(R.string.unable_to_fetch), Toast.LENGTH_SHORT)
                        .show();
                }
            }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timeline, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.asha_centre:
                Intent mapsAshaActivity = new Intent(TimelineActivity.this,
                        MapsActivity.class);
                mapsAshaActivity.putExtra(MapsActivity.TYPE, "Asha Center");
                startActivity(mapsAshaActivity);
                break;

            case R.id.bhamashah_centre:
                Intent mapsBhamashahActivity = new Intent(TimelineActivity.this,
                        MapsActivity.class);
                mapsBhamashahActivity.putExtra(
                        MapsActivity.TYPE, "Bhamashah Center");
                startActivity(mapsBhamashahActivity);
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
