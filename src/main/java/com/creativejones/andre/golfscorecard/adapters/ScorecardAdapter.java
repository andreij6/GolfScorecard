package com.creativejones.andre.golfscorecard.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.creativejones.andre.golfscorecard.R;
import com.creativejones.andre.golfscorecard.models.GolfHole;

import java.util.List;

public class ScorecardAdapter extends RecyclerView.Adapter<ScorecardAdapter.ViewHolder> {

    private static final String TAG = ScorecardAdapter.class.getSimpleName();

    private List<GolfHole> mScores;

    public ScorecardAdapter(List<GolfHole> scores){
        Log.d(TAG, "constructor");

        mScores = scores;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.scorecard_item, parent, false);
        Log.d(TAG, "onCreate View Holder");

        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(TAG, "binding view");
        holder.setScore(mScores.get(position));
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "count" + mScores.size());

        return mScores.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView Hole;
        private TextView Score;

        private Button Minus;
        private Button Plus;

        public ViewHolder(View itemView) {
            super(itemView);
            Hole = (TextView)itemView.findViewById(R.id.holeName);
            Score = (TextView)itemView.findViewById(R.id.holeScore);
            Minus = (Button)itemView.findViewById(R.id.minus);
            Plus = (Button)itemView.findViewById(R.id.plus);
        }

        public void setScore(final GolfHole score) {
            Hole.setText(score.getHoleName());
            setScoreValue(score);

            Minus.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    score.Minus();
                    setScoreValue(score);
                }
            });

            Plus.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    score.Add();
                    setScoreValue(score);
                }
            });
        }

        private void setScoreValue(GolfHole score){
            Score.setText(score.getScore() + "");
        }
    }
}
