package com.kukroid.greendot.model;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.kukroid.greendot.App;
import com.kukroid.greendot.contract.SummaryContract;
import com.kukroid.greendot.data.GreenDotPreferences;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by kukresa on 1/15/2019.
 */

public class SummaryModel  {
    private SummaryContract.summaryModel summaryModel;
    private ArrayList<FIBSeries> fibSeries = null;

    public SummaryModel(SummaryContract.summaryModel summaryModel) {
        this.summaryModel = summaryModel;
    }

    public void loadFIBData() {

            fibSeries = new ArrayList<>();
            GreenDotPreferences pref = GreenDotPreferences.getInstance(App.getContext());
            for (int i = 0; i < pref.getSize(); i++) {
                try {
                    FIBSeries fibDTO = pref.getFIBRecord(i);
                    fibSeries.add(fibDTO);
                }catch (Exception e){

                }
            }
            Collections.reverse(fibSeries );
            summaryModel.setDataToView(fibSeries);


        }

}
