package com.frek2816.androidlab;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    public static final String DETAILS = "fragment_details";
    public static final String FAVOURITES = "favourites";

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @BindView(R.id.favourites_button)
    protected FloatingActionButton goToFavouritesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ManageFragments manageFragments = new ManageFragments(this);
        ApplicationEx.getInstance().setFragmentManager(manageFragments);
        ApplicationEx.getInstance().getFragmentManager().setFragment(new ImageListFragment());

    }

    /*@OnClick(R.id.favourites_button)
    protected void onFavouritesButtonClick(View view){
        setFragment(new ImageFavouritesFragment());
    }*/

}