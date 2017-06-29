/*
package com.example.r20np.seller.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.r20np.seller.R;
import com.example.r20np.seller.data.model.CurrentAddress;

import java.util.ArrayList;

*/
/**
 * Created by R20NP on 2016-04-24.
 *//*

public class PopupListAdapter extends BaseAdapter implements View.OnClickListener{
    ArrayList<CurrentAddress> mItems = new ArrayList<>();


    Context context ;


    public PopupListAdapter(Context context) {

        this.context =context.getApplicationContext();
    }

    public void addData(ArrayList<CurrentAddress> Items){
        this.mItems=Items;
    }

    public ArrayList<CurrentAddress> getmItems() {
        return mItems;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position).getDistrict();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder holder;

            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_popuplist, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
                if (mItems.get(0).getDistrict().equals("현재 위치로 설정")) {
                    if (position==0){
                        holder.iv.setImageResource(R.drawable.gpson);
                        holder.iv1.setImageResource(0);
                    }else {
                        holder.iv1.setImageResource(R.drawable.cross);
                        holder.iv.setImageResource(R.drawable.location_pointer);
                    }
                }else {
                    holder.iv.setImageResource(R.drawable.location_pointer);
                    holder.iv1.setImageResource(0);
                    if (mItems.size()==1){
                        onClick(convertView);
                    }
                }
        holder.tv.setText(mItems.get(position).getDistrict());

        holder.iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.list_iv1:
                        new DistrictDatabase(context).deleteList(getItem(position).toString());
                        mItems.remove(position);

                        notifyDataSetChanged();
                        break;
                }

            }
        });



            return convertView;
        }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.list_iv1:
                //Toast.makeText(context,,Toast.LENGTH_LONG).show();
                break;
        }
    }


    static class ViewHolder  {
        TextView tv;
        ImageView iv, iv1;
        LinearLayout ll;

        public ViewHolder(View itemView) {

            tv = (TextView) itemView.findViewById(R.id.list_tv);
            iv = (ImageView) itemView.findViewById(R.id.list_iv);
            iv1 = (ImageView) itemView.findViewById(R.id.list_iv1);
            ll= (LinearLayout) itemView.findViewById(R.id.item_ll);



        }
    }
}
*/


