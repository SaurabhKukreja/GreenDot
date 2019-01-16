package com.kukroid.greendot.contract;

import com.kukroid.greendot.model.FIBSeries;

/**
 * Created by kukresa on 1/10/2019.
 */

public interface MainActivityContract {

    interface MainActivityView{
        void updateTime(int n);
        void setDataInSpinner(FIBSeries n);
        void numberNotValid();
    }

    interface MainActivityModel{

        void getFOfNumber(FIBSeries n);

    }
}
