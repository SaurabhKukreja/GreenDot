package com.kukroid.greendot.contract;

import com.kukroid.greendot.model.FIBSeries;

import java.util.ArrayList;

/**
 * Created by kukresa on 1/15/2019.
 */

public interface SummaryContract {

    interface summaryView {

        void setDataToRecylerView(ArrayList<FIBSeries> fibSeries);

    }
    interface summaryModel{

        void fillData();

        void setDataToView(ArrayList<FIBSeries> fibSeries);
    }

}
