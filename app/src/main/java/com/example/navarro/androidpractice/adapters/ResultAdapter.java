package com.example.navarro.androidpractice.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anavrrropc.practicejanb.R;
import com.example.navarro.androidpractice.beans.unpersistible.Result;

import java.util.List;

/**
 * Created by ${Navarro} on 9/14/17.
 */

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {

    //region Variables
    private List<Result> mResultsList;
    //endregion

    //region Constructor
    public ResultAdapter(List<Result> mResultsList) {
        this.mResultsList = mResultsList;
    }
    //endregion

    //region Adapter Methods
    @Override
    public ResultAdapter.ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false);
        return  new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ResultAdapter.ResultViewHolder holder, int position) {
        Result item = mResultsList.get(position);
        holder.bindResult(item);
    }

    @Override
    public int getItemCount() {
        return mResultsList.size();
    }
    //endregion

    //region Classes
    public class ResultViewHolder extends RecyclerView.ViewHolder {
        private TextView mName, mCode2, mCode3;
        public ResultViewHolder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.item_county_name);
            mCode2 = itemView.findViewById(R.id.item_county_code2);
            mCode3 = itemView.findViewById(R.id.item_county_code3);
        }

        public void bindResult(Result item) {
            mName.setText(item.getName());
            mCode2.setText(item.getAlpha2Code());
            mCode3.setText(item.getAlpha3Code());
        }
    }
    //endregion
}
