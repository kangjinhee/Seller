package com.example.r20np.seller.util;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.io.File;

import cn.lightsky.infiniteindicator.ImageLoader;


/**
 * Created by lightsky on 16/1/28.
 */

public class PicassoLoader implements ImageLoader {
    private Picasso picasso;

    public PicassoLoader(Picasso picasso) {
        this.picasso = picasso;
    }

    public PicassoLoader getImageLoader(Context context) {
        return new PicassoLoader(picasso);
    }




    @Override
    public void load(Context context, ImageView targetView, Object res) {
        if (res == null) {
            return;
        }

        RequestCreator requestCreator = null;

        if (res instanceof String) {
            requestCreator = picasso.load((String) res);
        } else if (res instanceof File) {
            requestCreator = picasso.load((File) res);
        } else if (res instanceof Integer) {
            requestCreator = picasso.load((Integer) res);
        }
        if(requestCreator != null) {
            requestCreator
                    .fit()
                    .into(targetView);
        }
    }
}
