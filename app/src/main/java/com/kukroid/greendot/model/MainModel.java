package com.kukroid.greendot.model;

import com.kukroid.greendot.contract.MainActivityContract;
import com.kukroid.greendot.presenter.MainActivityPresenter;

/**
 * Created by kukresa on 1/10/2019.
 */

public class MainModel {

    private MainActivityPresenter mainActivityPresenter;
    int result = 0;

    public MainModel(MainActivityPresenter mainActivityPresenter) {
        this.mainActivityPresenter = mainActivityPresenter;
    }

    public int getFIB(int n) {
        if (n <= 1)
            return  n;
        return getFIB(n-1) + getFIB(n-2);

    }

    public void updateSpinnerData(int n, int result) {

        FIBSeries fibSeries = new FIBSeries();
        fibSeries.setN(n);
        fibSeries.setfOfN(result);

        mainActivityPresenter.getFOfNumber(fibSeries);

    }
}
