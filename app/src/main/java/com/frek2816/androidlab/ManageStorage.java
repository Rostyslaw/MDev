package com.frek2816.androidlab;

import android.content.Context;
import android.content.SharedPreferences;

import com.frek2816.androidlab.Entity.ImageItem;
import com.google.gson.Gson;

import java.util.Map;

import static com.frek2816.androidlab.MainActivity.FAVOURITES;

public class ManageStorage {
    private final SharedPreferences mPrefs;

    public ManageStorage( Context context) {
        mPrefs = context.getSharedPreferences(FAVOURITES, Context.MODE_PRIVATE);
    }

    public void addToStorage(ImageItem imageItem){
        mPrefs.edit().putString(imageItem.getFilename(), new Gson().toJson(imageItem)).commit();
    }

    public void removeFromStorage(ImageItem imageItem) {
        mPrefs.edit().remove(imageItem.getFilename()).commit();
    }

    public boolean ifStorageContainsItem (String fileName) {
        if (mPrefs.contains(fileName)){
            return true;
        } else {
            return false;
        }
    }

    public Map<String, ?> getAllFromStorage(){
        return mPrefs.getAll();
    }
}
