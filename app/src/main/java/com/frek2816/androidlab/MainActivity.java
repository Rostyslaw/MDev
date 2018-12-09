package com.frek2816.androidlab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private List<ImageItem> imageList;
    private ImageAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRecyclerAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setJsonToRecyclerAdapter();
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
            }

            @Override
            public void onFailure(Call<List<ImageItem>> call, Throwable t) {
                Toast.makeText(MainActivity.this,
                        "Something went wrong...Error message: "
                                + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}