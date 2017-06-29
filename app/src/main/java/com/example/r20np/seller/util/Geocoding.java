package com.example.r20np.seller.util;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by R20NP on 2016-04-06.
 */
public class Geocoding {


    public static Address geocding(Context context, String address, double lat, double lng){
        Address curAddress =null;
        List<Address> addressList =null;

        try {
            Geocoder coder = new Geocoder(context, Locale.KOREAN);
            if (address!=null) {

                 addressList = coder.getFromLocationName(address, 1);

            }else if(lat!=0&&lng!=0){
                 addressList = coder.getFromLocation(lat, lng, 1);
            }
            if (addressList != null) {
                for (int i = 0; i < addressList.size(); i++) {
                    curAddress = addressList.get(i);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return curAddress;
    }
}
