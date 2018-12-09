package com.frek2816.androidlab;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.frek2816.androidlab.MainActivity.DETAILS;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private List<ImageItem> imageItemList;
    private Context context;

    public ImageAdapter(Context context, List<ImageItem> imageItemList) {
        this.context = context;
        this.imageItemList = imageItemList;
    }

    public void setImageList(List<ImageItem> imageItemList) {
        this.imageItemList = imageItemList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ImageAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.single_recycler_view, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder,
                                 @SuppressLint("RecyclerView") final int position) {
        holder.authorTextView.setText(imageItemList.get(position).getAuthor());
        holder.formatTextView.setText(imageItemList.get(position).getFormat());
        holder.filenameTextView.setText(imageItemList.get(position).getFilename());
        Picasso.with(holder.itemView.getContext())
                .load("https://picsum.photos/200/300?image="
                        + imageItemList.get(position).getId())
                .centerCrop().fit().into(holder.imageView);
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageDetailsFragment imageDetailsFragment = new ImageDetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putString(DETAILS, new Gson().toJson(imageItemList.get(position)));
                imageDetailsFragment.setArguments(bundle);
                ((MainActivity) view.getContext()).setFragment(imageDetailsFragment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageItemList.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.json_author)
        protected TextView authorTextView;

        @BindView(R.id.json_format)
        protected TextView formatTextView;

        @BindView(R.id.json_file_name)
        protected TextView filenameTextView;

        @BindView(R.id.image)
        protected ImageView imageView;

        @BindView(R.id.parent_layout)
        CardView parentLayout;

        ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}