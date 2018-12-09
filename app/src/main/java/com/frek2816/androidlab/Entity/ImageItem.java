package com.frek2816.androidlab.Entity;

import com.google.gson.annotations.SerializedName;

public class ImageItem {

    @SerializedName("format")
    private String format;

    @SerializedName("width")
    private int width;

    @SerializedName("height")
    private int height;

    @SerializedName("filename")
    private String filename;

    @SerializedName("id")
    private int id;

    @SerializedName("author")
    private String author;

    @SerializedName("author_url")
    private String authorUrl;

    @SerializedName("post_url")
    private String postUrl;

    public ImageItem(String format, int width, int height, String filename,
                     int id, String author, String authorUrl, String postUrl) {
        super();
        this.format = format;
        this.width = width;
        this.height = height;
        this.filename = filename;
        this.id = id;
        this.author = author;
        this.authorUrl = authorUrl;
        this.postUrl = postUrl;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorUrl() {
        return authorUrl;
    }

    public void setAuthorUrl(String authorUrl) {
        this.authorUrl = authorUrl;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }

}