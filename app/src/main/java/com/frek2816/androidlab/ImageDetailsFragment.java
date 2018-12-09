package com.frek2816.androidlab;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.frek2816.androidlab.MainActivity.DETAILS;
import static com.frek2816.androidlab.MainActivity.FAVOURITES;

public class ImageDetailsFragment extends Fragment {
    private static final int HEIGHT = 1800;
    private Bundle bundle;
    private SharedPreferences sharedPreferences;
    private ImageItem imageItem;
    private boolean isImageFitToScreen;

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
        bundle = this.getArguments();
        getFragmentData();
        setData();
        checkFavourite();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlImage();
            }
        });
        return view;
    }

    @OnClick(R.id.add_favourite_button)
    protected void onAddFavouriteButtonClick(View view) {
        editFavourites();
    }

    private void getFragmentData() {
        if (bundle != null) {
            imageItem = new Gson().fromJson(bundle.getString(DETAILS), ImageItem.class);
        }
    }

    private void setData() {
        Picasso.with(getContext()).load("https://picsum.photos/200/300?image=" +
                imageItem.getId()).into(imageView);
        fileName.setText(imageItem.getFilename());
        author.setText(imageItem.getAuthor());
        width.setText(String.valueOf(imageItem.getWidth()));
        format.setText(imageItem.getFormat());
        sharedPreferences = Objects.requireNonNull(getActivity())
                .getSharedPreferences(FAVOURITES, Context.MODE_PRIVATE);
    }

    private void checkFavourite() {
        if (sharedPreferences.contains(imageItem.getFilename()))
            addToFavouritesButton.setImageResource(R.drawable.ic_favorite_white_24dp);
    }

    private void editFavourites() {
        if (!sharedPreferences.contains(imageItem.getFilename())) {
            addToFavouritesButton.setImageResource(R.drawable.ic_favorite_white_24dp);
            sharedPreferences.edit().putString(imageItem.getFilename(), new Gson().toJson(imageItem)).apply();
        } else {
            addToFavouritesButton.setImageResource(R.drawable.ic_favorite_border_white_24dp);
            sharedPreferences.edit().remove(imageItem.getFilename()).apply();
        }
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
}