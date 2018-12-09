package com.frek2816.androidlab;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.frek2816.androidlab.Entity.ImageItem;
import com.frek2816.androidlab.Presenters.DetailsPresenterImpl;
import com.frek2816.androidlab.Presenters.PresenterInterfaces;
import com.frek2816.androidlab.Views.ViewInterfaces;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.frek2816.androidlab.MainActivity.FAVOURITES;

public class ImageDetailsFragment extends Fragment implements ViewInterfaces.DetailsView {
    private static final int HEIGHT = 1800;
    private boolean isImageFitToScreen;
    private PresenterInterfaces.DetailsPresenter presenter;

    @BindView(R.id.image_details)
    protected ImageView imageView;

    @BindView(R.id.fileName)
    protected TextView fileName;

    @BindView(R.id.author)
    protected TextView author;

    @BindView(R.id.width)
    protected TextView width;

    @BindView(R.id.format)
    protected TextView format;

    @BindView(R.id.add_favourite_button)
    protected ImageView addToFavouritesButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, view);
        presenter = new DetailsPresenterImpl(this);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlImage();
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getData();
    }

    @OnClick(R.id.add_favourite_button)
    protected void onAddFavouriteButtonClick(View view) {
        presenter.checkFavourite();
    }


    @Override
    public void initializeItems(ImageItem imageItem) {
        Picasso.with(getContext()).load("https://picsum.photos/200/300?image=" +
                imageItem.getId()).into(imageView);
        fileName.setText(imageItem.getFilename());
        author.setText(imageItem.getAuthor());
        width.setText(String.valueOf(imageItem.getWidth()));
        format.setText(imageItem.getFormat());
    }

    @Override
    public void addToFavourites() {
        addToFavouritesButton.setImageResource(R.drawable.ic_favorite_white_24dp);
    }

    @Override
    public void removeFromFavourites(){
        addToFavouritesButton.setImageResource(R.drawable.ic_favorite_border_white_24dp);
    }

    private void controlImage() {
        if (isImageFitToScreen) {
            isImageFitToScreen = false;
            imageView.setLayoutParams(new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT, HEIGHT));
        } else {
            isImageFitToScreen = true;
            imageView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams
                    .MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
    }

    @Override
    public void editFavourite(boolean favourite) {
        if (favourite) {
            addToFavouritesButton.setImageResource(R.drawable.ic_favorite_white_24dp);
            Log.e("hdjfglglf", "HERE HERE HERE HERE");
        }
    }
}