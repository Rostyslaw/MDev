package com.frek2816.androidlab;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.frek2816.androidlab.Entity.ImageItem;
import com.frek2816.androidlab.Presenters.ListPresenterImpl;
import com.frek2816.androidlab.Presenters.PresenterInterfaces;
import com.frek2816.androidlab.Views.ViewInterfaces;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageListFragment extends Fragment implements ViewInterfaces.ListImagesView {
    private List<ImageItem> imageList;
    private ImageAdapter imageAdapter;
    private PresenterInterfaces.ListPresenter listPresenter;

    @BindView(R.id.no_data_image)
    protected ImageView noDataImage;

    @BindView(R.id.swipe_refresh_layout)
    protected SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.recycler_view)
    protected RecyclerView recyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        noDataImage.setVisibility(View.INVISIBLE);
        listPresenter = new ListPresenterImpl(this);
        initializeAdapter();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        listPresenter.getData();
    }

    private void initializeAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void setData(List<ImageItem> imageItems) {
        imageAdapter= new ImageAdapter(getActivity(), imageItems);
        recyclerView.setAdapter(imageAdapter);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(getActivity(), "Unsa"
                + throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }


}
