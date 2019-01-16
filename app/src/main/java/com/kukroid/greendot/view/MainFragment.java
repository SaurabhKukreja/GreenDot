package com.kukroid.greendot.view;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.kukroid.greendot.R;
import com.kukroid.greendot.Utils.Util;
import com.kukroid.greendot.adapter.RecyclerViewAdapter;
import com.kukroid.greendot.contract.MainActivityContract;
import com.kukroid.greendot.data.GreenDotPreferences;
import com.kukroid.greendot.model.FIBSeries;
import com.kukroid.greendot.presenter.MainActivityPresenter;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class MainFragment extends Fragment implements View.OnClickListener, MainActivityContract.MainActivityView {

    private MainActivityPresenter presenter;
    EditText number;
    RecyclerView recyclerView;
    RelativeLayout layout;
    long startTime;
    TextView timeTaken;
    List<FIBSeries> list;
    int N ;
    RecyclerViewAdapter adapter;
    View view;
    Button goAhead;
    private GreenDotPreferences pref;
    android.support.v7.widget.Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.home_fragment,container,false);
        setHasOptionsMenu(true);
        init();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter = new MainActivityPresenter(this);
    }

    private void init() {

        list = new ArrayList<>();
        number = view.findViewById(R.id.number);
        recyclerView = view.findViewById(R.id.spinner);
        layout = view.findViewById(R.id.layout);
        timeTaken = view.findViewById(R.id.timeTaken);
        goAhead = view.findViewById(R.id.goButtone);
        goAhead.setOnClickListener(this);
        adapter = new RecyclerViewAdapter(getContext(), list);
        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        pref = GreenDotPreferences.getInstance(getContext());

        number.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                timeTaken.setText("");
                return false;

            }
        });

    }

    private void goAhead(){

        try {
            InputMethodManager imm = (InputMethodManager)getContext().getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
            N = Integer.parseInt(number.getText().toString());
            startTime = System.currentTimeMillis();
            presenter.checkFofNumber(N);
            number.setText("");


        }catch (Exception e){

            numberNotValid();
        }

    }

    @Override
    public void updateTime(int fn) {

        long currentTime = System.currentTimeMillis();
        long timeTakenValue = currentTime - startTime ;
        timeTaken.setText(timeTakenValue + "ms");

    }

    @Override
    public void setDataInSpinner(FIBSeries n) {

        list.add(n);
        pref.saveRecord(n);
        adapter.notifyDataSetChanged();


    }

    @Override
    public void numberNotValid() {
        Util.showSnackBar(layout,R.string.error_msg);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.goButtone:
                goAhead();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.summary) {
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.container,new SummaryFragment(),"SummaryFragment");
            transaction.addToBackStack("SummaryFragment");
            transaction.commit();
        }

        return super.onOptionsItemSelected(item);
    }
}
