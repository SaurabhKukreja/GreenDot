package com.kukroid.greendot.presenter;

import com.kukroid.greendot.contract.MainActivityContract;
import com.kukroid.greendot.model.FIBSeries;
import com.kukroid.greendot.model.MainModel;

/**
 * Created by kukresa on 1/10/2019.
 */

public class MainActivityPresenter implements MainActivityContract.MainActivityModel{

    private final MainModel mainActivityModel;
    private MainActivityContract.MainActivityView mainContractView;

    public MainActivityPresenter(MainActivityContract.MainActivityView mainContractView) {

        this.mainContractView = mainContractView;
        this.mainActivityModel = new MainModel(this);
    }

    public void checkFofNumber(int n) {
        if(n >= 0 && n <= 9 ){

            int result =  mainActivityModel.getFIB(n);
            mainContractView.updateTime(n);
            mainActivityModel.updateSpinnerData(n , result);

        }else {
            mainContractView.numberNotValid();

        }
    }

    @Override
    public void getFOfNumber(FIBSeries n) {
        mainContractView.setDataInSpinner(n);
    }

}
