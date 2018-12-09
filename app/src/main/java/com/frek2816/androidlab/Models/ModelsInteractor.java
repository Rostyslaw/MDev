package com.frek2816.androidlab.Models;

import com.frek2816.androidlab.Entity.ImageItem;

import java.util.List;

public interface ModelsInteractor {
    interface ListInteractor {
        interface OnFinishedListener {
            void onFinishedLoad(List<ImageItem> imageItems);
            void onFailure(Throwable t);
        }
        void getData(ModelsInteractor.ListInteractor.OnFinishedListener
                                        onFinishedListener);

    }

    interface DetailsInteractor{
        interface OnFinishedListener {
            void onAdd();
            void onRemove();
            void favouriteResult(boolean change);
        }
        void manageFavourites(ImageItem imageItem, OnFinishedListener onFinishedListener);
        void checkFavourite(ImageItem imageItem, ModelsInteractor.DetailsInteractor.
                OnFinishedListener onFinishedListener);
    }
}
