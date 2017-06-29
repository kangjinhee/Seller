package com.example.r20np.seller.ui.presenter;

import android.util.Log;

import com.example.r20np.seller.data.model.Advertisement;
import com.example.r20np.seller.ui.contract.MainActivityContract;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import cn.lightsky.infiniteindicator.Page;

import static android.content.ContentValues.TAG;

/**
 * Created by R20NP on 2017-05-09.
 */

public class MainActivityPresenter implements MainActivityContract.Presenter{
    private MainActivityContract.View mView;
    ArrayList<Page> pageViews = new ArrayList<>();

    public MainActivityPresenter(MainActivityContract.View mView) {
        this.mView = mView;
    }
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference("advertisement");
    @Override
    public void getAD() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
             for (DataSnapshot adSnapshot : dataSnapshot.getChildren()){
                 Advertisement ad = adSnapshot.getValue(Advertisement.class);
                 pageViews.add(new Page(ad.getUrl(),ad.getImage_path()));
             }
                mView.showAD(pageViews);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });
    }


}
