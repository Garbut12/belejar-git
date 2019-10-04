package com.riyan.sub3cataloguemovieapi;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class MovieItem  implements Parcelable {
    private int id;
    private String nama, detail, foto;

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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public MovieItem() {

    }

    MovieItem(JSONObject object) {
        try {
            int id = object.getInt("id");
            String nama = object.getString("original_title");
            String detail = object.getString("overview");
            String photo = object.getString("poster_path");

            this.id = id;
            this.nama = nama;
            this.detail = detail;
            this.foto = photo;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected MovieItem(Parcel in) {
        id = in.readInt();
        nama = in.readString();
        detail = in.readString();
        foto = in.readString();
    }

    public static final Creator<MovieItem> CREATOR = new Creator<MovieItem>() {
        @Override
        public MovieItem createFromParcel(Parcel in) {
            return new MovieItem(in);
        }

        @Override
        public MovieItem[] newArray(int size) {
            return new MovieItem[size];
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
        parcel.writeString(foto);
    }
}
