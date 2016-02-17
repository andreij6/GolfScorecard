package com.creativejones.andre.golfscorecard.views;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.creativejones.andre.golfscorecard.R;
import com.creativejones.andre.golfscorecard.adapters.ScorecardAdapter;
import com.creativejones.andre.golfscorecard.models.GolfHole;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_FILE = "com.creativejones.andre.golfscorecard";
    private static final String KEY_STROKECOUNT = "stroke_count_key";

    RecyclerView mRecyclerView;
    ScorecardAdapter mAdapter;
    List<GolfHole> mScoreData;

    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        setTitle(R.string.golf_scorecard);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_clear_strokes){

            for(GolfHole score : mScoreData){
                score.setScore(0);
            }

            mRecyclerView.swapAdapter(new ScorecardAdapter(mScoreData), false);

            mEditor.clear();
            mEditor.apply();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();

        for(int i = 0; i < 18; i++){
            mEditor.putInt(KEY_STROKECOUNT + i, mScoreData.get(i).getScore());
        }

        mEditor.apply();
    }

    private void setRecyclerView() {
        mScoreData = initialScoreData();

        mRecyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setNestedScrollingEnabled(false);

        mAdapter = new ScorecardAdapter(mScoreData);
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<GolfHole> initialScoreData() {
        List<GolfHole> result = new ArrayList<>(18);

        for(int i = 0; i < 18; i++) {
            int strokes = mSharedPreferences.getInt(KEY_STROKECOUNT + i, 0);

            result.add(new GolfHole("Hole " + (i + 1) + " : ", strokes));
        }

        return result;
    }

}
