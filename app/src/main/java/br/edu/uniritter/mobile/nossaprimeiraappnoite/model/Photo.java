package br.edu.uniritter.mobile.nossaprimeiraappnoite.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

public class Photo implements Parcelable {
    public static final Creator<Photo> CREATOR = new Creator<Photo>() {
        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };
    private final int albumId;
    private int id;
    private String title;
    private String url;
    private String thumbnailUrl;

    public Photo(int postId, int id, String title, String url, String thumbnailUrl) {
        this.albumId = postId;
        this.id = id;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;

    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private Photo(Parcel parcel) {
        this.albumId = parcel.readInt();
        this.id = parcel.readInt();
        this.title = parcel.readString();
        this.url = parcel.readString();
        this.thumbnailUrl = parcel.readString();
    }

    public int getPostId() {
        return albumId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getAlbumId() {
        return albumId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.albumId);
        parcel.writeInt(this.id);
        parcel.writeString(this.title);
        parcel.writeString(this.url);
        parcel.writeString(this.thumbnailUrl);

    }
}
