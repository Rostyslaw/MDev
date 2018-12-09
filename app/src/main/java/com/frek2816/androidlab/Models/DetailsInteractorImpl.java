package com.frek2816.androidlab.Models;

import com.frek2816.androidlab.Entity.ImageItem;
import com.frek2816.androidlab.ManageStorage;

public class DetailsInteractorImpl implements ModelsInteractor.DetailsInteractor{

    ManageStorage storageManager;

    public DetailsInteractorImpl(ManageStorage storageManager){
        this.storageManager = storageManager;
    }

    public void manageFavourites(ImageItem imageItem,
                                 ModelsInteractor.DetailsInteractor.
                                         OnFinishedListener onFinishedListener){
        if (!storageManager.ifStorageContainsItem(imageItem.getFilename())) {
            storageManager.addToStorage(imageItem);
            onFinishedListener.onAdd();
        } else {
            storageManager.removeFromStorage(imageItem);
            onFinishedListener.onRemove();
        }
    }

    @Override
    public void checkFavourite(ImageItem imageItem, ModelsInteractor.DetailsInteractor.
            OnFinishedListener onFinishedListener) {
        if (storageManager.ifStorageContainsItem(imageItem.getFilename())) {
            onFinishedListener.favouriteResult(true);
        } else {
            onFinishedListener.favouriteResult(false);
        }
    }
}
