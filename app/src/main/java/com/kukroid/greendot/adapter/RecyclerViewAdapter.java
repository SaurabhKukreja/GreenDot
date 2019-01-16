package com.kukroid.greendot.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.kukroid.greendot.R;
import com.kukroid.greendot.model.FIBSeries;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kukresa on 1/11/2019.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyRecyclerView> {



    private final Context mContext;
    private final List<FIBSeries> items;

    public RecyclerViewAdapter(Context context, List<FIBSeries> list){
        this.mContext = context;
        this.items = list;

    }


    @Override
    public MyRecyclerView onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.spinner_layout,parent,false);
        return new MyRecyclerView(view);
    }

    @Override
    public void onBindViewHolder(MyRecyclerView holder, int position) {
        FIBSeries series = items.get(position);
        holder.nInt.setText(series.getN()+"");
        holder.f_N_Int.setText(series.getfOfN()+"");
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyRecyclerView extends RecyclerView.ViewHolder{
        TextView nInt;
        TextView f_N_Int;

        public MyRecyclerView(View itemView) {
            super(itemView);
            nInt = (TextView) itemView.findViewById(R.id.n);
            f_N_Int = (TextView) itemView.findViewById(R.id.f_n);

        }
    }

}
