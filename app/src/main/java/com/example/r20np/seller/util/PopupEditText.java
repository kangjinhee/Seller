/*
package com.example.r20np.seller.util;


import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.Address;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.r20np.seller.R;
import com.example.r20np.seller.data.model.CurrentAddress;
import com.example.r20np.seller.ui.activity.MainActivity;

import java.util.ArrayList;


public class PopupEditText extends AppCompatEditText implements View.OnFocusChangeListener ,TextView.OnEditorActionListener,AdapterView.OnItemClickListener{

    public PopupWindow popupWindow;
    private MainActivity mainActivity;
    View anchorVeiw ;

    private ListView listView;
    PopupListAdapter adapter;


    DisplayMetrics dm;




    public PopupEditText(Context context) {
        super(context);
        init(context);

    }

    public PopupEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PopupEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(final Context context) {
        Log.d("test", "onFocusChange:focus");
        Resources res = getResources();
        dm = res.getDisplayMetrics();
        Log.d("theme",context.getTheme().toString());
        oncreatePopupWindow(context.getApplicationContext());
        mainActivity = (MainActivity) scanForActivity(context);


        super.setOnFocusChangeListener(this);
        super.setOnEditorActionListener(this);
    }

    private PopupWindow oncreatePopupWindow(Context context){
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 294, dm);


        listView = new ListView(context);
        listView.setDividerHeight(4);
        listView.setBackgroundResource(R.color.white);
        listView.setCacheColorHint(Color.parseColor("#000000"));
        listView.setOnItemClickListener(this);
        adapter = new PopupListAdapter(context);
        listView.setAdapter(adapter);




        popupWindow = new PopupWindow(context);
        popupWindow.setWidth(width);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(GetDrawColor.getDrawable(getContext(), android.R.color.white));

        popupWindow.setContentView(listView);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);

        Log.d("test", "popupWindow");
        return popupWindow;
    }


    private void showCurrentList(){
        Log.d("test", "showCurrentList");
        ArrayList<CurrentAddress> data = mainActivity.myDatabase.getList();
        showDropdown(getContext(), data);
    }

    private void showDropdown(Context context, ArrayList<CurrentAddress> data){
        if(popupWindow.isShowing()){
            popupWindow.dismiss();
        }
        int offset = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, dm);
        View v =mainActivity.findViewById(R.id.popupbackground);
        v.setVisibility(VISIBLE);
        adapter.addData(data);
        popupWindow.showAsDropDown(mainActivity.findViewById(R.id.main_rl_contain_edit), 0, offset);

        if (adapter.getCount()==1){
            listView.performItemClick(listView.getAdapter().getView(0, null, null), 0, listView.getAdapter().getItemId(0));
        }
    }

    private void dismissDropdown(){
        View v =mainActivity.findViewById(R.id.popupbackground);
        v.setVisibility(INVISIBLE);

        popupWindow.dismiss();
    }

    public void move(int verticalOffset){
        Log.d("off", "move" + verticalOffset);
        mainActivity.findViewById(R.id.main_rl_contain_edit).getScrollY();
        int offset = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, dm);

        popupWindow.update(mainActivity.findViewById(R.id.main_rl_contain_edit), -1, offset, -1, -1);

    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        mainActivity.iv.setVisibility(INVISIBLE);
        mainActivity.ll.setVisibility(INVISIBLE);
        if (hasFocus) {
            mainActivity.iv.setVisibility(View.VISIBLE);
            mainActivity.appBarLayout.setExpanded(false);
            showCurrentList();
            mainActivity.getSupportActionBar().setHomeAsUpIndicator(0);
            mainActivity.HOMEICON=1;
            Log.d("test", "onFocusChange:focus");
        } else {
            Log.d("test", "onFocusChange:focus-false");
            mainActivity.ll.setVisibility(VISIBLE);
            mainActivity.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
            mainActivity.HOMEICON=0;
            if(popupWindow.isShowing()){
                dismissDropdown();
            }
        }
    }


    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        Log.d("actionId",""+actionId);
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            if(popupWindow.isShowing()){
                dismissDropdown();}
            if(getText().length()==0){
                Toast.makeText(getContext(), "동 이름을 입력하세요.", Toast.LENGTH_SHORT).show();
                return true;
            }else if (getText().length()>0&&getText().length()<2){
                Toast.makeText(getContext(), "2글자 이상 입력하세요.", Toast.LENGTH_SHORT).show();
                return true;
            }else {
                String search = getText().toString();
                Toast.makeText(getContext(), search, Toast.LENGTH_SHORT).show();

                InputMethodManager inputManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(this.findFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
               // new GetDitrict(getContext()).execute(search);

                return true;
            }
        }
        return false;

    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        Log.d("test", "keyCode" + keyCode);
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == 1) {
            Log.d("test", "keyEvent-back");

            InputMethodManager inputManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if(inputManager.hideSoftInputFromWindow(findFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS)){
                return false;
            }
            clearFocus();
            Log.d("test", "clearFocus");
        }
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (adapter.getItem(position).equals("현재 위치로 설정")){
                mainActivity.myLocation1.checkLocationSettings();
        }else {
            setText(adapter.getItem(position).toString());
            clearFocus();
            mainActivity.myDatabase.addList(new CurrentAddress(adapter.getItem(position).toString()));
            Address address = Geocoding.geocding(getContext(), adapter.getItem(position).toString(), 0, 0);
            Store.storeAddress(getContext(),address);
        }
    }


   */
/* public class GetDitrict extends AsyncTask<String, Void, ArrayList<CurrentAddress>> {

        private String url ="http://1.247.107.54:8181/DBConnection/getdistrict.jsp";

        Context mContext;
        ProgressDialog progressDialog;
        private JSONArray mJsonarray = null;
        private JSONParser jsonParser = new JSONParser();
        private ArrayList<CurrentAddress> data= new ArrayList<>();


        public GetDitrict(Context context){
            mContext=context;
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("목록을 가져오는 중입니다.");
        }

        @Override
        protected void onPreExecute() {
            progressDialog.show();
        }

        @Override
        protected ArrayList<CurrentAddress> doInBackground(String... params) {
            HashMap<String,String> param = new HashMap<>();
            param.put("param",params[0]);
            try {
                JSONObject json = jsonParser.makeHttpRequest(url,"POST",param);
                if(json.length() != 0) {
                    JSONArray districts =json.getJSONArray("districts");

                    for (int i=0; i<districts.length();i++){
                        JSONObject c = districts.getJSONObject(i);
                        String district = c.getString("district");
                        data.add(new CurrentAddress(district));
                        Log.d("jin", "for문");
                    }


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return data;
        }

        @Override
        protected void onPostExecute(ArrayList<CurrentAddress> data) {

            showDropdown(getContext(), data);
            progressDialog.dismiss();
        }
    }*//*









  */
/*  private PopupWindow oncreatePopupWindow(Context context){

        inflater = LayoutInflater.from(context);
        contentView = inflater.inflate(R.layout.item_recyclerview, null);
*//*
*/
/*        RelativeLayout rv= (RelativeLayout) contentView.findViewById(R.id.list_item_ll);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);*//*
*/
/*

    *//*
*/
/*    RelativeLayout rl= (RelativeLayout) mainActivity.findViewById(R.id.rl);
        layoutParams.width= rl.getWidth();
        layoutParams.rightMargin = 8;
        Log.d("width",""+rl.getWidth());*//*
*/
/*
        contentView.measure(WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow = new PopupWindow(context);
        RecyclerView recyclerView = (RecyclerView) contentView.findViewById(R.id.list_item_rv);
        SellerListAdapter adapter = new SellerListAdapter((Activity)context,arr);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        //popupWindow.setFocusable(true);

        popupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);

        // popupWindow.getInputMethodMode(popupWindow.)
        // popupWindow.setOutsideTouchable(true);
        popupWindow.setHeight(WindowManager.LayoutParams.MATCH_PARENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(android.R.color.black)));

        popupWindow.setContentView(contentView);




        popupWindow.showAtLocation(mainActivity.findViewById(R.id.rl), Gravity.RIGHT, 0, 0);

        return popupWindow;
    }*//*


    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        RelativeLayout rl = (RelativeLayout) mainActivity.findViewById(R.id.main_rl_contain_edit);
        rl.getMeasuredWidth();
        rl.getMeasuredHeight();
        rl.getWidth();
        rl.getHeight();


        Log.d("wh", "1" + rl.getMeasuredWidth() + "2" + rl.getMeasuredHeight() + "3" + rl.getWidth() + "4" + rl.getHeight() + "LL:" );


    }
    public static Activity scanForActivity(Context cont) {
        if (cont == null)
            return null;
        else if (cont instanceof Activity)
            return (Activity)cont;
        else if (cont instanceof ContextWrapper)
            return scanForActivity(((ContextWrapper)cont).getBaseContext());

        return null;
    }

}
*/
