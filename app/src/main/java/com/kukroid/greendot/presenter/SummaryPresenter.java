package com.kukroid.greendot.presenter;

import com.kukroid.greendot.contract.SummaryContract;
import com.kukroid.greendot.model.FIBSeries;
import com.kukroid.greendot.model.SummaryModel;

import java.util.ArrayList;

/**
 * Created by kukresa on 1/15/2019.
 */

public class SummaryPresenter implements SummaryContract.summaryModel{

    private final SummaryModel summaryModel;
    private SummaryContract.summaryView summaryView;

    public SummaryPresenter(SummaryContract.summaryView summaryView) {
        this.summaryView = summaryView;
        summaryModel = new SummaryModel(this);
    }

    @Override
    public void fillData() {
       summaryModel.loadFIBData();
    }

    @Override
    public void setDataToView(ArrayList<FIBSeries> fibSeries) {

        summaryView.setDataToRecylerView(fibSeries);

    }
}
