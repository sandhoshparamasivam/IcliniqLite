package com.orane.icliniqlite;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.flurry.android.FlurryAgent;
import com.github.ksoichiro.android.observablescrollview.ObservableListView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.orane.icliniqlite.Model.Item;
import com.orane.icliniqlite.Model.Model;
import com.orane.icliniqlite.adapter.ArticleListAdapter;
import com.orane.icliniqlite.network.JSONParser;
import com.orane.icliniqlite.network.NetCheck;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Articles_List_Activity extends AppCompatActivity implements View.OnClickListener {

    ArrayAdapter<String> dataAdapter = null;
    Item objItem;
    public List<Item> listArray;
    public List<Item> arrayOfList;
    public File imageFile;
    JSONObject jsonobj_makefav;
    ViewPager vpPager;
    FloatingActionButton fab;

    ProgressBar progressBar, progressBar_bottom;
    Toolbar toolbar;
    SwipeRefreshLayout mSwipeRefreshLayout;
    ObservableListView listView;
    LinearLayout bg_layout, nolayout, netcheck_layout;
    String title_text, art_url, str_response;
    public boolean pagination = true;

    ArticleListAdapter objAdapter;
    Map<String, String> spec_map = new HashMap<String, String>();
    public String tv_id_val, Doc_id, spec_val = "0", fav_url;
    long startTime;


    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Login_Status = "Login_Status_key";
    public static final String user_name = "user_name_key";
    public static final String Name = "Name_key";
    public static final String password = "password_key";
    public static final String isValid = "isValid";
    public static final String id = "id";
    public static final String browser_country = "browser_country";
    public static final String email = "email";
    public static final String fee_q = "fee_q";
    public static final String fee_consult = "fee_consult";
    public static final String fee_q_inr = "fee_q_inr";
    public static final String fee_consult_inr = "fee_consult_inr";
    public static final String currency_symbol = "currency_symbol";
    public static final String currency_label = "currency_label";
    public static final String have_free_credit = "have_free_credit";
    public static final String photo_url = "photo_url";
    public static final String sp_km_id = "sp_km_id_key";
    public static final String first_query = "first_query_key";

    public String sub_url = "sapp/articles";
    public String Log_Status;
    LinearLayout appBarLayout;
    boolean _isFirsTtime = true;
    Double floor_val;
    Integer int_floor;
    Button btn_book_copns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.articles_list);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.app_color2));
        }

        //================ Shared Pref ===============================
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        Log_Status = sharedpreferences.getString(Login_Status, "");
        Model.name = sharedpreferences.getString(Name, "");
        Model.id = sharedpreferences.getString(id, "");
        //============================================================

        bg_layout = findViewById(R.id.bg_layout);
        progressBar = findViewById(R.id.progressBar);
        progressBar_bottom = findViewById(R.id.progressBar_bottom);
        netcheck_layout = findViewById(R.id.netcheck_layout);
        nolayout = findViewById(R.id.nolayout);
        mSwipeRefreshLayout = findViewById(R.id.swipe_query_new);
        listView = findViewById(R.id.listview);
        fab = findViewById(R.id.fab);


        try {
            FlurryAgent.onPageView();

            //------------ Google firebase Analitics--------------------
            Model.mFirebaseAnalytics = FirebaseAnalytics.getInstance(Articles_List_Activity.this);
            Bundle params = new Bundle();
            params.putString("User", Model.id);
            Model.mFirebaseAnalytics.logEvent("Articles_List", params);
            //------------ Google firebase Analitics--------------------

        } catch (Exception ee) {
            ee.printStackTrace();
        }


        full_process();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Articles_List_Activity.this, SpecialityListActivity.class);
                startActivity(intent);

            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                System.out.println("position-----" + position);

                TextView tv_id = view.findViewById(R.id.tv_id);
                TextView tv_url = view.findViewById(R.id.tv_url);
                TextView textview_title = view.findViewById(R.id.article_title);


                tv_id_val = tv_id.getText().toString();
                art_url = tv_url.getText().toString();
                title_text = textview_title.getText().toString();

                Intent intent = new Intent(Articles_List_Activity.this, ArticleViewActivity.class);
                intent.putExtra("img_url", "");
                intent.putExtra("title", title_text);
                intent.putExtra("KEY_ctype", "2");
                intent.putExtra("KEY_url", art_url);
                intent.putExtra("id", tv_id_val);
                startActivity(intent);

                //------------ Google firebase Analitics--------------------
                Model.mFirebaseAnalytics = FirebaseAnalytics.getInstance(Articles_List_Activity.this);
                Bundle params = new Bundle();
                params.putString("User", Model.id);
                Model.mFirebaseAnalytics.logEvent("Articles_List", params);
                //------------ Google firebase Analitics--------------------

            }
        });


        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

                int threshold = 1;
                int count = listView.getCount();
                System.out.println("count----- " + count);

                if (scrollState == SCROLL_STATE_IDLE) {
                    if (listView.getLastVisiblePosition() >= count - threshold) {

                        double cur_page = (listView.getAdapter().getCount()) / 10;
                        System.out.println("cur_page 1----" + cur_page);

                        if (count < 10) {
                            System.out.println("No more booking(s) to Load");
                            //Toast.makeText(getApplicationContext(), "No more booking(s) to Load", Toast.LENGTH_LONG).show();
                            int_floor = 0;
                        } else if (count == 10) {
                            floor_val = cur_page + 1;
                            System.out.println("Final Val----" + floor_val);
                            int_floor = floor_val.intValue();

                        } else {
                            floor_val = Math.floor(cur_page);
                            Double diff = cur_page - floor_val;

                            System.out.println("cur_page 2----" + cur_page);
                            System.out.println("floor_val 2----" + floor_val);
                            System.out.println("diff 2----" + diff);

                            if (diff == 0) {
                                floor_val = floor_val + 1;
                            } else if (diff > 0) {
                                floor_val = floor_val + 2;
                            }

                            System.out.println("Final Val----" + floor_val);
                            int_floor = floor_val.intValue();
                        }

                        if (int_floor != 0 && (pagination)) {
                            try {
                                //-----------------------------------------
                                String DoctorList_url = Model.BASE_URL + "app/articles?page=" + int_floor;
                                System.out.println("DoctorList_url-----" + DoctorList_url);
                                new MyTask_Pagination().execute(DoctorList_url);
                                //-----------------------------------------
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if (new NetCheck().netcheck(Articles_List_Activity.this)) {
                    pagination = true;

                    //-----------------------------------------------------
                    String DoctorList_url = Model.BASE_URL + "app/articles?page=1";
                    System.out.println("DoctorList_url-------------------" + DoctorList_url);
                    new MyTask_server().execute(DoctorList_url);
                    //-----------------------------------------------------

                    mSwipeRefreshLayout.setRefreshing(false);

                } else {

                    mSwipeRefreshLayout.setVisibility(View.GONE);
                    nolayout.setVisibility(View.GONE);
                    netcheck_layout.setVisibility(View.VISIBLE);
                    progressBar_bottom.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                    bg_layout.setVisibility(View.GONE);
                }
            }
        });

        // listView.setScrollViewCallbacks(this);

    }


    public void full_process() {

        Model.select_spec_val = "0";

        if (isInternetOn()) {

            if ((Model.id) != null && !(Model.id).isEmpty() && !(Model.id).equals("null") && !(Model.id).equals("")) {

                //-------------------------------------------------------------------------------
                String DoctorList_url = Model.BASE_URL + "app/articles?page=1";
                System.out.println("DoctorList_url------------" + DoctorList_url);
                new MyTask_server().execute(DoctorList_url);
                //-------------------------------------------------------------------------------
            }

        } else {
            mSwipeRefreshLayout.setVisibility(View.GONE);
            nolayout.setVisibility(View.GONE);
            netcheck_layout.setVisibility(View.VISIBLE);
            progressBar_bottom.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            bg_layout.setVisibility(View.GONE);

        }
    }


    class MyTask_server extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            nolayout.setVisibility(View.GONE);
            mSwipeRefreshLayout.setVisibility(View.GONE);
            netcheck_layout.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            bg_layout.setVisibility(View.VISIBLE);

            progressBar_bottom.setVisibility(View.GONE);

            startTime = System.currentTimeMillis();
        }

        @Override
        protected Void doInBackground(String... params) {
            try {

                str_response = new JSONParser().getJSONString(params[0]);
                System.out.println("Article--------------" + str_response);

                return null;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            try {
                //----------------------------------------------------------
                JSONArray jsonarr = new JSONArray(str_response);
                listArray = new ArrayList<Item>();

                for (int i = 0; i < jsonarr.length(); i++) {

                    JSONObject jsonobj1 = jsonarr.getJSONObject(i);

                    objItem = new Item();
                    objItem.setId(jsonobj1.getString("articleId"));
                    objItem.setArturl(jsonobj1.getString("url"));
                    objItem.setArtDocname(jsonobj1.getString("doctor_name"));
                    objItem.setArtTitle(jsonobj1.getString("title"));
                    objItem.setArtAbs(jsonobj1.getString("abstract"));
                    objItem.setArtimgurl(jsonobj1.getString("photo_url"));

/*
                    String photo_url = ().replaceAll("(?<!http:)//", "/");
                    System.out.println("photo_url===========" + photo_url);
*/

                    objItem.setCdurl(jsonobj1.getString("article_photo_url"));

                    objItem.setSurl(jsonobj1.getString("share_url"));

                    listArray.add(objItem);

                }

                arrayOfList = listArray;
                if (null == arrayOfList || arrayOfList.size() == 0) {

                    nolayout.setVisibility(View.VISIBLE);
                    mSwipeRefreshLayout.setVisibility(View.GONE);
                    netcheck_layout.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                    bg_layout.setVisibility(View.GONE);

                    progressBar_bottom.setVisibility(View.GONE);

                } else {

                    if (arrayOfList.size() < 10) {
                        pagination = false;
                    }

                    nolayout.setVisibility(View.GONE);
                    mSwipeRefreshLayout.setVisibility(View.VISIBLE);
                    netcheck_layout.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                    bg_layout.setVisibility(View.GONE);

                    progressBar_bottom.setVisibility(View.GONE);

                    long elapsedTime = System.currentTimeMillis() - startTime;
                    System.out.println("Total Elapsed Doctor List SERVER time in milliseconds: " + elapsedTime);

                    setAdapterToListview();
                }
                //----------------------------------------------------------

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    class MyTask_Pagination extends AsyncTask<String, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            nolayout.setVisibility(View.GONE);
            mSwipeRefreshLayout.setVisibility(View.VISIBLE);
            netcheck_layout.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            bg_layout.setVisibility(View.GONE);

            progressBar_bottom.setVisibility(View.VISIBLE);

            startTime = System.currentTimeMillis();
        }

        @Override
        protected Void doInBackground(String... params) {
            try {
                str_response = new JSONParser().getJSONString(params[0]);
                System.out.println("str_response--------------" + str_response);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            try {
                //----------------------------------------------------------
                listArray = new ArrayList<Item>();
                JSONArray jsonarr = new JSONArray(str_response);


                if (str_response.length() > 2) {

                    listArray = new ArrayList<Item>();

                    for (int i = 0; i < jsonarr.length(); i++) {

                        JSONObject jsonobj1 = jsonarr.getJSONObject(i);

                        objItem = new Item();
                        objItem.setArturl(jsonobj1.getString("url"));
                        objItem.setArtDocname(jsonobj1.getString("doctor_name"));
                        objItem.setArtTitle(jsonobj1.getString("title"));
                        objItem.setArtAbs(jsonobj1.getString("abstract"));
                        objItem.setArtimgurl(jsonobj1.getString("photo_url"));
                        objItem.setCdurl(jsonobj1.getString("article_photo_url"));
                        objItem.setSurl(jsonobj1.getString("share_url"));

                        listArray.add(objItem);
                    }

                    arrayOfList = listArray;
                    if (null == arrayOfList || arrayOfList.size() == 0) {

                        nolayout.setVisibility(View.GONE);
                        mSwipeRefreshLayout.setVisibility(View.VISIBLE);
                        netcheck_layout.setVisibility(View.GONE);
                        progressBar.setVisibility(View.GONE);
                        bg_layout.setVisibility(View.GONE);

                        progressBar_bottom.setVisibility(View.GONE);

                    } else {

                        if (arrayOfList.size() < 10) {
                            pagination = false;
                        }

                        nolayout.setVisibility(View.GONE);
                        mSwipeRefreshLayout.setVisibility(View.VISIBLE);
                        netcheck_layout.setVisibility(View.GONE);
                        progressBar.setVisibility(View.GONE);
                        bg_layout.setVisibility(View.GONE);

                        progressBar_bottom.setVisibility(View.GONE);

                        long elapsedTime = System.currentTimeMillis() - startTime;
                        System.out.println("Total Elapsed Doctor List SERVER time in milliseconds: " + elapsedTime);

                        add_page_AdapterToListview();
                    }
                }
                //----------------------------------------------------------

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setAdapterToListview() {

        objAdapter = new ArticleListAdapter(Articles_List_Activity.this, R.layout.article_list_row, arrayOfList);
        listView.setAdapter(objAdapter);
        objAdapter.notifyDataSetChanged();
    }

    public void add_page_AdapterToListview() {

        objAdapter.addAll(arrayOfList);
        listView.setSelection(objAdapter.getCount() - (arrayOfList.size()));
        objAdapter.notifyDataSetChanged();
    }

    public final boolean isInternetOn() {

        ConnectivityManager connec = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {
            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {
            return false;
        }

        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //getMenuInflater().inflate(R.menu.ask_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }

       /* if (id == R.id.nav_info) {
            // show_tips();
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }


    public void onArticlesClick(View v) {

        try {

            View grand_parent = (View) v.getParent();
            View grand_grand_parent = (View) grand_parent.getParent();
            View grand_grand_grand_parent = (View) grand_grand_parent.getParent();

            TextView tv_share_url = v.findViewById(R.id.tv_share_url);
            TextView article_title = grand_grand_grand_parent.findViewById(R.id.article_title);

            String shareurl = tv_share_url.getText().toString();
            String articletitle = article_title.getText().toString();

            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(Intent.EXTRA_TEXT, articletitle + "\n\nRead the article on iCliniq.. " + shareurl);
            startActivity(Intent.createChooser(sharingIntent, "Share using"));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
