package com.frek2816.androidlab;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

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
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        holder.authorTextView.setText(imageItemList.get(position).getAuthor());
        holder.formatTextView.setText(imageItemList.get(position).getFormat());
        holder.filenameTextView.setText(imageItemList.get(position).getFilename());
    }

    @Override
    public int getItemCount() {
        return imageItemList.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {

        private TextView authorTextView;
        private TextView formatTextView;
        private TextView filenameTextView;

        ImageViewHolder(View itemView) {
            super(itemView);
            authorTextView = itemView.findViewById(R.id.json_author);
            formatTextView = itemView.findViewById(R.id.json_format);
            filenameTextView = itemView.findViewById(R.id.json_file_name);
        }
    }
}