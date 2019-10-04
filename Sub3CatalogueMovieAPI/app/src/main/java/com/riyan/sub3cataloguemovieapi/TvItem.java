package com.riyan.sub3cataloguemovieapi;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class TvItem implements Parcelable {
    private int id;
    private String nama,detail, photo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    TvItem(JSONObject object) {
        try {
            int id = object.getInt("id");
            String nama = object.getString("original_name");
            String detail = object.getString("overview");
            String photo = object.getString("poster_path");

            this.id = id;
            this.nama = nama;
            this.detail = detail;
            this.photo = photo;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TvItem() {

    }



    protected TvItem(Parcel in) {
        id = in.readInt();
        nama = in.readString();
        detail = in.readString();
        photo = in.readString();
    }

    public static final Creator<TvItem> CREATOR = new Creator<TvItem>() {
        @Override
        public TvItem createFromParcel(Parcel in) {
            return new TvItem(in);
        }

        @Override
        public TvItem[] newArray(int size) {
            return new TvItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(nama);
        parcel.writeString(detail);
        parcel.writeString(photo);
    }
}
