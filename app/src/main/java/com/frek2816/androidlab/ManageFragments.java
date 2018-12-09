package com.frek2816.androidlab;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.frek2816.androidlab.Entity.ImageItem;
import com.google.gson.Gson;

import static com.frek2816.androidlab.MainActivity.DETAILS;

public class ManageFragments {

    private final MainActivity activity;
    private Fragment currentFragment;

    ManageFragments(MainActivity activity){
        this.activity = activity;
    }

   public void setFragment(final Fragment fragment){
       activity.getSupportFragmentManager().
               beginTransaction()
               .replace(R.id.frame_container, fragment)
               .addToBackStack(null)
               .commit();
       currentFragment = fragment;
   }

   public ImageItem getArguments(){
       if (currentFragment instanceof ImageDetailsFragment) {
           final Bundle arguments = currentFragment.getArguments();
           if (arguments != null) {
               ImageItem imageItem = new Gson().fromJson(arguments.getString(DETAILS),
                       ImageItem.class);
               return imageItem;
           }
       }
       return null;
   }

   public void setArguments(final ImageItem imageItem){
       final ImageDetailsFragment fragment = new ImageDetailsFragment();
       final Bundle bundle = new Bundle();
       bundle.putString(DETAILS, new Gson().toJson(imageItem));
       fragment.setArguments(bundle);
       setFragment(fragment);
   }

}
