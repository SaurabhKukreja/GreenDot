package com.kukroid.greendot.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.kukroid.greendot.R;
import com.kukroid.greendot.Utils.Util;
import com.kukroid.greendot.adapter.SummaryViewAdapter;
import com.kukroid.greendot.contract.SummaryContract;
import com.kukroid.greendot.model.FIBSeries;
import com.kukroid.greendot.presenter.SummaryPresenter;

import java.util.ArrayList;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by kukresa on 1/15/2019.
 */

public class SummaryFragment extends Fragment implements SummaryContract.summaryView{

    View view;
    RecyclerView summaryRecycler;
    private ArrayList<FIBSeries> fibSeries = null;
    private SummaryViewAdapter adapter;
    private SummaryPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.summary_fragment,container,false);
        setHasOptionsMenu(true);
        init();
        return view;
    }

    private void init() {

        presenter = new SummaryPresenter(this);
        summaryRecycler = view.findViewById(R.id.summary);
        fibSeries = new ArrayList<FIBSeries>();
        adapter = new SummaryViewAdapter(getContext(), fibSeries);
        summaryRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        summaryRecycler.setAdapter(adapter);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        }catch (Exception e){
            Util.log("Exception: "+e.getMessage());
        }
        presenter.fillData();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuItem item = menu.findItem(R.id.summary);
        item.setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void setDataToRecylerView(ArrayList<FIBSeries> fibSeries) {
        this.fibSeries.addAll(fibSeries);
        adapter.notifyDataSetChanged();
    }

}
