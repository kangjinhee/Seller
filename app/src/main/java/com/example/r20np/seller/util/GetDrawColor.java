package com.example.r20np.seller.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;

/**
 * Created by R20NP on 2016-04-26.
 */
public class GetDrawColor {

    @TargetApi(Build.VERSION_CODES.M)
    public static int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 21) {
            return context.getColor(id);
        } else {
            return context.getResources().getColor(id);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static Drawable getDrawable(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getDrawable(id);
        } else {
            return context.getResources().getDrawable(id);
        }
    }
}
