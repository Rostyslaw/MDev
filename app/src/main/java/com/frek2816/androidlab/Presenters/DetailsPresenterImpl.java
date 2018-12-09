package com.frek2816.androidlab.Presenters;

import android.support.transition.Visibility;

import com.frek2816.androidlab.ApplicationEx;
import com.frek2816.androidlab.Entity.ImageItem;
import com.frek2816.androidlab.Models.ModelsInteractor;
import com.frek2816.androidlab.Views.ViewInterfaces;

public class DetailsPresenterImpl implements PresenterInterfaces.DetailsPresenter,
        ModelsInteractor.DetailsInteractor.OnFinishedListener {

    private ModelsInteractor.DetailsInteractor interactor;
    private ViewInterfaces.DetailsView view;
    private ImageItem imageItem;

    public DetailsPresenterImpl(ViewInterfaces.DetailsView view){
        this.view = view;
        interactor = ApplicationEx.getInstance().getDetailsInteractor();
    }

    public void getData() {
        imageItem = ApplicationEx.getInstance().getFragmentManager().getArguments();
        view.initializeItems(imageItem);
        interactor.checkFavourite(imageItem, this);
    }

    public void checkFavourite() {
        interactor.manageFavourites(imageItem, this);
    }
    public void onAdd() {
        view.addToFavourites();
    }

    public void onRemove() {
        view.removeFromFavourites();
    }
    public void favouriteResult(boolean favourite) {
        view.editFavourite(favourite);
    }



}
