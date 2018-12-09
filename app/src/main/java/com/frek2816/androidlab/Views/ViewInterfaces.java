package com.frek2816.androidlab.Views;

import com.frek2816.androidlab.Entity.ImageItem;

import java.util.List;

public interface ViewInterfaces {
    interface ListImagesView {
        void setData(List<ImageItem> imageItems);
        void onResponseFailure(Throwable throwable);
    }

    interface DetailsView {
        void addToFavourites();
        void removeFromFavourites();
        void editFavourite(boolean favourite);
        void initializeItems(ImageItem imageItem);
    }
}
