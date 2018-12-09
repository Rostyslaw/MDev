package com.frek2816.androidlab;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageListFragment extends Fragment {
    private List<ImageItem> imageList;
    private ImageAdapter imageAdapter;

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

        setRecyclerAdapter();
        refreshPageContent();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setJsonToRecyclerAdapter();
    }

    private void refreshPageContent() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setJsonToRecyclerAdapter();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void setRecyclerAdapter() {
        imageList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        imageAdapter = new ImageAdapter(getContext(), imageList);
        recyclerView.setAdapter(imageAdapter);
    }

    private void setJsonToRecyclerAdapter() {
        GetImageDataService service = RetrofitInstance
                .getRetrofitInstance().create(GetImageDataService.class);
        Call<List<ImageItem>> call = service.getImageList();

        call.enqueue(new Callback<List<ImageItem>>() {
            @Override
            public void onResponse(Call<List<ImageItem>> call, Response<List<ImageItem>> response) {
                assert response.body() != null;
                imageList = response.body();
                imageAdapter.setImageList(imageList);
                noDataImage.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<ImageItem>> call, Throwable t) {
                noDataImage.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(),
                        getString(R.string.message)
                                + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
