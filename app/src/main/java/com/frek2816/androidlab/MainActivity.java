package com.frek2816.androidlab;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<ImageItem> imageList;
    private ImageAdapter recyclerAdapter;

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @BindView(R.id.swipe_refresh_layout)
    protected SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.no_data_image)
    protected ImageView noDataImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        noDataImage.setVisibility(View.INVISIBLE);

        setRecyclerAdapter();
        refreshPageContent();
    }

    @Override
    protected void onResume() {
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


    private void setRecyclerAdapter(){
        imageList = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new ImageAdapter(getApplicationContext(), imageList);
        recyclerView.setAdapter(recyclerAdapter);
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
                recyclerAdapter.setImageList(imageList);
                noDataImage.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<ImageItem>> call, Throwable t) {
                noDataImage.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this,
                        getString(R.string.message)
                                + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}