package com.example.r20np.seller.data.model;

import android.os.Parcel;
import android.os.Parcelable;


public class Advertisement implements Parcelable {
    public String url;
    public String image_path;


    public Advertisement() {

    }

    public Advertisement(Integer id, String image_path, String url) {
       // this.id = id;
        this.image_path = image_path;
        this.url = url;
    }

    public String getImage_path() {return image_path;}

    public String getUrl() {return url;}

    //public Integer getId() {return id;}

    protected Advertisement(Parcel in) {
       // id = in.readInt();
        image_path = in.readString();
        url = in.readString();
    }

    public static final Creator<Advertisement> CREATOR = new Creator<Advertisement>() {
        @Override
        public Advertisement createFromParcel(Parcel in) {
            return new Advertisement(in);
        }

        @Override
        public Advertisement[] newArray(int size) {
            return new Advertisement[size];
        }
    };

    @Override
    public int describeContents() {return 0;}

    @Override
    public void writeToParcel(Parcel dest, int flags) {
     //   dest.writeInt(this.id);
        dest.writeString(this.image_path);
        dest.writeString(this.url);
    }
}
