package com.frek2816.androidlab;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.frek2816.androidlab.Entity.ImageItem;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.frek2816.androidlab.MainActivity.FAVOURITES;

public class ImageFavouritesFragment extends Fragment {

    private List<ImageItem> mItemsList = new ArrayList<>();

    @BindView(R.id.recycler_view)
    protected RecyclerView recyclerView;

    @BindView(R.id.no_data_image)
    protected ImageView noDataImage;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        getFavouriteItems();
        setRecyclerAdapter();
        noDataImage.setVisibility(View.INVISIBLE);
        return view;
    }

    private void getFavouriteItems() {
        SharedPreferences sharedPreferences = Objects.requireNonNull(getActivity())
                .getSharedPreferences(FAVOURITES, Context.MODE_PRIVATE);
        Map<String, ?> map = sharedPreferences.getAll();

        for (Map.Entry<String, ?> entry : map.entrySet()) {
            ImageItem imageItem = new Gson().fromJson(entry.getValue().toString(), ImageItem.class);
            mItemsList.add(imageItem);
        }
    }

    private void setRecyclerAdapter(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        ImageAdapter recyclerAdapter = new ImageAdapter(getContext(), mItemsList);
        recyclerView.setAdapter(recyclerAdapter);
    }
}