package com.frek2816.androidlab.Presenters;

public interface PresenterInterfaces {
    interface ListPresenter{
        void getData();
    }
    interface DetailsPresenter {
        void checkFavourite();
        void getData();
    }
}
