package com.orane.icliniqlite;

import android.Manifest;
import android.animation.ValueAnimator;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.nineoldandroids.view.ViewHelper;
import com.orane.icliniqlite.Model.Model;
import com.orane.icliniqlite.network.JSONParser;
import com.orane.icliniqlite.network.NetCheck;
import com.orane.icliniqlite.network.ShareIntent;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import me.drakeet.materialdialog.MaterialDialog;

public class Lite_Home_Activity extends AppCompatActivity {

    Intent intent;
    ImageView img_share_icon, offer_image2, img_search_logo;
    public File imageFile;
    TextView tv_offer_text, btn_free, offer_desc2, offer_title2, textview_title, textview_short, textview_docname, textview_ctype, offer_title, hotlinechat2, tv_pvcons2, offer_desc;
    LinearLayout offer_full_layout, queries_layout, innerLay, layout_offer1, layout_offer2;
    LinearLayout layout1, logo_layout, layout3, layout2, home_button, myhealth_layout, hotline_layout, phone_consult_layout, consult_layout, doc_layout, myquery_layout;
    TextView tv_chat_sub_text, tv_sub_chat, tv_chat, butt_text, tv_viewall, tv_url, how_title, how_desc, hotlinechat, tvdesc, tv_pvcons, tv_askquery;
    Bitmap bitmap_image;
    JSONObject logout_jsonobj;
    ObservableScrollView scrollview;
    SwipeRefreshLayout mSwipeRefreshLayout;
    String isActivePlan;
    JSONObject logout_json_validate;
    JSONArray jsonarray, jsonarr, jsonarr_banner;
    PendingIntent pIntent;
    ImageView offer_image1;
    JSONObject json_feedback, json_response_obj;
    Bitmap bitmap_images;
    CircleImageView imageview_poster;
    public String home_dets_list, logout_text, deals_offers_list, report_response, chat_tip_val, str_response_banner, qa_photo_url, qa_abstract, qa_title, qa_doctor_name, qa_url, str_response, ticker_text, ContentTitle, ContentText, SummaryText, photo_url, speciality, title_hash, title;
    Typeface font_reg, fonr_bold;
    Intent i;
    View vi;
    public HashMap<String, String> url_maps;
    public HashMap<String, String> url_maps_id;
    ImageView img_offer_banner;
    JSONObject qa_jsonobject, jsonobj1;
    private ImageView foundDevice;

    //SlidingTabLayout sliding_tabs;
    //public LinearLayout bottom_bar;

    Toolbar toolbar;

    private static final String ARG_TEXT = "arg_text";
    private static final String ARG_COLOR = "arg_color";

    SharedPreferences sharedpreferences;
    public static final String noti_status = "noti_status_key";
    public static final String noti_sound = "noti_sound_key";
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Login_Status = "Login_Status_key";
    public static final String app_language = "app_language_key";
    public static final String user_name = "user_name_key";
    public static final String password = "password_key";
    public static final String Name = "Name_key";
    public static final String mobile_no = "mobile_no_key";
    public static final String bcountry = "bcountry_key";
    public static final String app_first_open = "app_first_open_key";
    public static final String token = "token_key";
    public static final String browser_country = "browser_country";
    public static final String sp_km_id = "sp_km_id_key";
    public static final String isValid = "isValid";
    public static final String id = "id";
    public static final String email = "email";
    public static final String fee_q = "fee_q";
    public static final String fee_consult = "fee_consult";
    public static final String fee_q_inr = "fee_q_inr";
    public static final String fee_consult_inr = "fee_consult_inr";
    public static final String currency_symbol = "currency_symbol";
    public static final String currency_label = "currency_label";
    public static final String have_free_credit = "have_free_credit";
    public static final String sp_mcode = "sp_mcode_key";
    public static final String sp_mnum = "sp_mnum_key";
    public static final String sp_has_free_follow = "sp_has_free_follow_key";
    public static final String chat_tip = "chat_tip_key";
    public String uname, docname, pass, stop_noti_val, noti_sound_val;
    ImageView q_logo, articles_img, healthtools_img;
    LinearLayout layout_AskQuery, deals_layout, queries_list_layout;

    TextView tv_wallet, tv_emailid, tv_mobno, tv_title, tv_sub_title, tv_all_answers, tv_viewall_offers;
    Button btn_getitnow, btn_buynow;
    ImageView settings_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_fragment);

        //--------------------------------------------------------------------
/*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Ask a Query");
        }
*/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.app_color2));
        }
        //----------- initialize --------------------------------------


        //-------- Initialization -----------------------------------------------------
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        Model.id = sharedpreferences.getString(id, "");
        System.out.println("Model.id---------------------" + Model.id);
        Model.email = sharedpreferences.getString(email, "");
        Model.name = sharedpreferences.getString(Name, "");
        Model.mobile_no = sharedpreferences.getString(mobile_no, "");
        Model.first_time = "Yes";
        //-------- Initialization -----------------------------------------------------

        Model.have_free_credit = "";

        Model.mFirebaseAnalytics = FirebaseAnalytics.getInstance(Lite_Home_Activity.this);

        try {
            FirebaseInstanceId.getInstance().getInstanceId()
                    .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                        @Override
                        public void onComplete(@NonNull Task<InstanceIdResult> task) {
                            if (!task.isSuccessful()) {
                                //Log.w(TAG, "getInstanceId failed", task.getException());
                                return;
                            }

                            // Get new Instance ID token
                            String token = task.getResult().getToken();
                            System.out.println("Manually Genrated token--------------" + token);

                            //-------------------------------------------------------------------
                            String fcm_url = Model.BASE_URL + "sapp/updateDeviceRegId?reg_id=" + token + "&user_id=" + (Model.id) + "&v=" + Model.App_ver_slno + "&token=" + Model.token;
                            System.out.println("fcm_url---------" + fcm_url);
                            new JSON_FCM_Regid().execute(fcm_url);
                            //-------------------------------------------------------------------

                            //  new JSON_FCM_Regid().execute(gcm_url);
                        }
                    });

        } catch (Exception e) {
            System.out.println("Failed_Push........");
            e.printStackTrace();
        }


        font_reg = Typeface.createFromAsset(getAssets(), Model.font_name);
        fonr_bold = Typeface.createFromAsset(getAssets(), Model.font_name_bold);

        ((TextView) findViewById(R.id.tv_mysection)).setTypeface(font_reg);
        ((TextView) findViewById(R.id.tv_recent)).setTypeface(font_reg);

        offer_full_layout = findViewById(R.id.offer_full_layout);
        queries_layout = findViewById(R.id.queries_layout);
        tv_wallet = findViewById(R.id.tv_wallet);
        tv_mobno = findViewById(R.id.tv_mobno);
        tv_emailid = findViewById(R.id.tv_emailid);
        mSwipeRefreshLayout = findViewById(R.id.swipe_query_new);
        tv_all_answers = findViewById(R.id.tv_all_answers);
        queries_list_layout = findViewById(R.id.queries_list_layout);
        deals_layout = findViewById(R.id.deals_layout);
        scrollview = findViewById(R.id.scrollview);
        tv_pvcons2 = findViewById(R.id.tv_pvcons2);
        hotlinechat2 = findViewById(R.id.hotlinechat2);
        //tvdesc = (TextView) findViewById(R.id.tvdesc);
        //tv_sub_ask_query = (TextView) findViewById(R.id.tv_sub_ask_query);
        tv_chat = findViewById(R.id.tv_chat);
        tv_sub_chat = findViewById(R.id.tv_sub_chat);
        tv_chat_sub_text = findViewById(R.id.tv_chat_sub_text);
        tv_offer_text = findViewById(R.id.tv_offer_text);
        tv_askquery = findViewById(R.id.tv_askquery);
        hotlinechat = findViewById(R.id.hotlinechat);
        tv_pvcons = findViewById(R.id.tv_pvcons);
        offer_title = findViewById(R.id.offer_title);
        offer_desc = findViewById(R.id.offer_desc);
        //myhealth_layout = (LinearLayout) findViewById(R.id.myhealth_layout);
        //top_search_layout = (LinearLayout) findViewById(R.id.top_search_layout);
        hotline_layout = findViewById(R.id.hotline_layout);
        layout2 = findViewById(R.id.layout2);
        layout1 = findViewById(R.id.layout1);
        layout3 = findViewById(R.id.layout3);
        doc_layout = findViewById(R.id.doc_layout);
        myquery_layout = findViewById(R.id.myquery_layout);
        // seenas_layout = (LinearLayout) findViewById(R.id.seenas_layout);
        home_button = findViewById(R.id.home_button);
        innerLay = findViewById(R.id.innerLay);
        how_desc = findViewById(R.id.how_desc);
        how_title = findViewById(R.id.how_title);
        butt_text = findViewById(R.id.butt_text);
        img_offer_banner = findViewById(R.id.img_offer_banner);
        tv_viewall = findViewById(R.id.tv_viewall);
        btn_free = findViewById(R.id.btn_free);
        offer_desc2 = findViewById(R.id.offer_desc2);
        offer_title2 = findViewById(R.id.offer_title2);
        tv_title = findViewById(R.id.tv_title);
        tv_sub_title = findViewById(R.id.tv_sub_title);
        settings_icon = findViewById(R.id.img_search_logo);
        img_share_icon = findViewById(R.id.img_share_icon);
        layout3 = findViewById(R.id.layout3);
        offer_image1 = findViewById(R.id.offer_image1);
        logo_layout = findViewById(R.id.logo_layout);
        layout_offer1 = findViewById(R.id.layout_offer1);
        layout_offer2 = findViewById(R.id.layout_offer2);
        layout_AskQuery = findViewById(R.id.layout_AskQuery);
        offer_image2 = findViewById(R.id.offer_image2);
        tv_viewall_offers = findViewById(R.id.tv_viewall_offers);
        articles_img = findViewById(R.id.articles_img);
        healthtools_img = findViewById(R.id.healthtools_img);

        btn_getitnow = findViewById(R.id.btn_getitnow);
        btn_buynow = findViewById(R.id.btn_buynow);

        System.out.println("Model.mnum--------------" + Model.mnum);
        System.out.println("Model.email--------------" + Model.email);


        layout_AskQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model.query_launch = "MainActivity";
                Intent intent = new Intent(Lite_Home_Activity.this, AskQuery1.class);
                startActivity(intent);
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            }
        });

        tv_all_answers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Lite_Home_Activity.this, QueryActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            }
        });

        tv_viewall_offers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Lite_Home_Activity.this, Offers_List_activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            }
        });

        articles_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Lite_Home_Activity.this, Articles_List_Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            }
        });

        healthtools_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Lite_Home_Activity.this, WebViewActivity.class);
                intent.putExtra("url", Model.BASE_URL + "tool?web_view=true");
                intent.putExtra("type", "Health Tools");
                startActivity(intent);
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

                //------------ Google firebase Analitics--------------------
                Model.mFirebaseAnalytics = FirebaseAnalytics.getInstance(getApplicationContext());
                Bundle params = new Bundle();
                params.putString("User", Model.id + Model.first_time);
                Model.mFirebaseAnalytics.logEvent("Health_Tools", params);
                //------------ Google firebase Analitics--------------------
            }
        });


        btn_getitnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Lite_Home_Activity.this, AskQuery1.class);
                startActivity(intent);

/*                Intent intent = new Intent(Lite_Home_Activity.this, Instant_Chat.class);
                intent.putExtra("doctor_id", "0");
                intent.putExtra("plan_id", "");
                startActivity(intent);*/
            }
        });

        /*btn_buynow.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    System.out.println("Offer banner click---------------");

                    if ((Model.browser_country).equals("IN")) {

                    Model.query_launch = "prepack";

                    Intent intent = new Intent(Lite_Home_Activity.this, PrePackActivity.class);
                    startActivity(intent);
                    1.
                    FlurryAgent.logEvent("PrePackActivity");

                    } else {

                        Model.query_launch = "subscription";
                        Intent intent = new Intent(Lite_Home_Activity.this, SubscriptionPackActivity.class);
                        startActivity(intent);
                        FlurryAgent.logEvent("SubscriptionPackActivity");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
*/
/*
        //----------- Deals and Offers--------------------------------------------------------
        String get_family_url = Model.BASE_URL + "/sapp/listDealsAndOffers?page=1&limit=10&user_id=" + Model.id + "&isHomeReq=1";
        System.out.println("Home List of Offers---------" + get_family_url);
        new JSON_deals_offers().execute(get_family_url);
        //------------Deals and Offers-------------------------------------------------------*/


        //----------- Deals and Offers--------------------------------------------------------
        String get_home_dets = Model.BASE_URL + "/sapp/homeDet4LiteApp?user_id=" + Model.id + "&token=" + Model.token;
        System.out.println("get_home_dets---------" + get_home_dets);
        new JSON_Home_dets().execute(get_home_dets);
        //------------Deals and Offers-------------------------------------------------------


        q_logo = findViewById(R.id.q_logo);
        Animation shake = AnimationUtils.loadAnimation(Lite_Home_Activity.this, R.anim.shakeanim);
        q_logo.startAnimation(shake);

       /* final RippleBackground rippleBackground = findViewById(R.id.content);
        final Handler handler = new Handler();*/

        tv_title.setTypeface(fonr_bold);
        tv_sub_title.setTypeface(font_reg);

        //bottom_bar = (LinearLayout) findViewById(R.id.bottom_bar);
        //sliding_tabs = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        toolbar = findViewById(R.id.toolbar);

        tv_askquery.setTypeface(fonr_bold);
        tv_chat.setTypeface(fonr_bold);
        tv_sub_chat.setTypeface(font_reg);
        tv_chat_sub_text.setTypeface(font_reg);
        //tv_sub_ask_query.setTypeface(font_reg);

        tv_pvcons.setTypeface(fonr_bold);
        tv_pvcons2.setTypeface(font_reg);
        hotlinechat.setTypeface(fonr_bold);
        hotlinechat2.setTypeface(font_reg);
        offer_title.setTypeface(fonr_bold);
        offer_desc.setTypeface(font_reg);
        how_title.setTypeface(fonr_bold);
        how_desc.setTypeface(font_reg);
        butt_text.setTypeface(fonr_bold);

        Animation animSlideDown = AnimationUtils.loadAnimation(Lite_Home_Activity.this, R.anim.bounce);
        offer_image1.startAnimation(animSlideDown);

     /*   img_search_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Lite_Home_Activity.this, Search_Screen.class);
                intent.putExtra("search_type", "all");
                startActivity(intent);
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            }
        });
*/
        img_share_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ShareIntent sintent = new ShareIntent();
                    sintent.ShareApp(Lite_Home_Activity.this, "MainActivity");
                } catch (Exception e) {
                    //System.out.println("Exception-- Share_Intent---" + e.toString());
                    e.printStackTrace();
                }
            }
        });


        logo_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        q_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        settings_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Lite_Home_Activity.this, SettingsActivity.class);
                intent.putExtra("screen_launch", "settings");
                startActivity(intent);
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
            }
        });

/*
        Animation animSlideDown1 = AnimationUtils.loadAnimation(Lite_Home_Activity.this, R.anim.bounce);
        animSlideDown1.setStartOffset(100);
        layout1.startAnimation(animSlideDown1);
        Animation animSlideDown2 = AnimationUtils.loadAnimation(Lite_Home_Activity.this, R.anim.bounce2);

        animSlideDown2.setStartOffset(300);
        layout2.startAnimation(animSlideDown2);
        Animation animSlideDown3 = AnimationUtils.loadAnimation(Lite_Home_Activity.this, R.anim.bounce3);
        animSlideDown3.setStartOffset(500);
        layout3.startAnimation(animSlideDown3);

        Animation animSlideDown4 = AnimationUtils.loadAnimation(Lite_Home_Activity.this, R.anim.bounce4);
        animSlideDown4.setStartOffset(700);
        layout_offer1.startAnimation(animSlideDown4);
*/

      /*  //---------- Sneaker Button ----------------------------
        mainAction = new LGSnackbarAction("Action", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Lite_Home_Activity.this, "Action fired", Toast.LENGTH_LONG).show();
            }
        });
        ButterKnife.bind(getActivity());
        LGSnackbarManager.prepare(Lite_Home_Activity.this, LGSnackBarThemeManager.LGSnackbarThemeName.SHINE);
        //---------- Sneaker Button ----------------------------
*/

        if ((Model.browser_country) != null && !(Model.browser_country).isEmpty() && !(Model.browser_country).equals("null") && !(Model.browser_country).equals("")) {
            if (!(Model.browser_country).equals("IN")) {

                System.out.println("==================== Subscription Packages");

                offer_image2.setImageResource(R.mipmap.nonindia_subs);

                offer_title2.setText("Subscription Packages");
                offer_desc2.setText("Get UNLIMITED chat with doctors by monthly SUBSCRIPTION packages");
                //how_desc2.setText("iCliniq is #1 Online Second Opinion Platform");

                tv_pvcons.setText("Book a Phone/Video Chat");
                tv_pvcons2.setText("Chat with doctors on Phone/Video");
                tv_offer_text.setText("Subscription Packages");

            } else {

                System.out.println("==================== PrePaid Packages");

                offer_title2.setText("Great offers on prepaid packages");
                offer_image2.setImageResource(R.mipmap.prep);
            }

        } else {
            Model.browser_country = "IN";
        }

        try {
            System.out.println("Model.have_free_credit------------------------" + Model.have_free_credit);

            if ((Model.have_free_credit).equals("1")) {
                //btn_free.setText("(You have 1 FREE query credit)");
                btn_free.setVisibility(View.VISIBLE);
            } else {
                //btn_free.setText("Get medical advice from doctors");
                btn_free.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


/*        img_offer_banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("layout_offer1---------" + layout_offer1);
            }
        });*/

        layout_offer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


     /*   layout_offer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Model.query_launch = "Main";

                Intent intent = new Intent(Lite_Home_Activity.this, IntroScreenActivity.class);
                intent.putExtra("screen_launch", "home");
                startActivity(intent);
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);

                FlurryAgent.logEvent("HowitWorks");

                //noti();
            }
        });
*/


        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    Intent intent = new Intent(Lite_Home_Activity.this, QueryActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

                    //------------ Google firebase Analitics--------------------
                    Model.mFirebaseAnalytics = FirebaseAnalytics.getInstance(Lite_Home_Activity.this);
                    Bundle params = new Bundle();
                    params.putString("User", Model.id);
                    Model.mFirebaseAnalytics.logEvent("Home_MyQueries", params);
                    //------------ Google firebase Analitics--------------------

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //==================== Tool Tip =======================================================================
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        chat_tip_val = sharedpreferences.getString(chat_tip, "off");
        System.out.println("Get chat_tip_val------" + chat_tip_val);
        //-----------------------------------------------------

        //chat_tip_val="on";

        //============================================================
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(chat_tip, "off");
        editor.apply();
        System.out.println("Gone for Offline ------");
        //============================================================


        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if (new NetCheck().netcheck(Lite_Home_Activity.this)) {

                    //----------- Deals and Offers--------------------------------------------------------
                    String get_home_dets = Model.BASE_URL + "/sapp/homeDet4LiteApp?user_id=" + Model.id + "&token=" + Model.token;
                    System.out.println("get_home_dets---------" + get_home_dets);
                    new JSON_Home_dets().execute(get_home_dets);
                    //------------Deals and Offers-------------------------------------------------------

                    mSwipeRefreshLayout.setRefreshing(false);

                }
            }
        });
    }

    public void force_logout() {

        try {

            //------------ Google firebase Analitics--------------------
            Bundle params = new Bundle();
            params.putString("Country", (Model.browser_country));
            Model.mFirebaseAnalytics.logEvent("Force_Logout", params);
            //------------ Google firebase Analitics--------------------


            final MaterialDialog alert = new MaterialDialog(Lite_Home_Activity.this);
            alert.setTitle("Please re-login the app..!");
            alert.setMessage("Something went wrong. Please Logout and Login again to continue");
            alert.setCanceledOnTouchOutside(false);
            alert.setPositiveButton("OK", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //============================================================
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(Login_Status, "0");
                    editor.apply();
                    //============================================================

                    finishAffinity();
                    Intent i = new Intent(Lite_Home_Activity.this, LoginActivity.class);
                    startActivity(i);
                    alert.dismiss();
                    finish();

                    //-------------- Logout-------------------------------------------------
                    try {

                        logout_json_validate = new JSONObject();
                        logout_json_validate.put("user_id", Model.id);
                        logout_json_validate.put("reg_id", Model.device_token);
                        logout_json_validate.put("os_type", "1");

                        System.out.println("logout_json_validate----" + logout_json_validate.toString());

                        new JSON_logout().execute(logout_json_validate);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //--------------- Logout------------------------------------------------
                }
            });

            alert.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        try {
            System.out.println("Model.have_free_credit------------------------" + Model.have_free_credit);

            try {
                if ((Model.email) != null && !(Model.email).isEmpty() && !(Model.email).equals("null") && !(Model.email).equals("")) {
                    tv_emailid.setText("Email : " + Model.email);
                } else {
                    tv_emailid.setVisibility(View.GONE);
                }

                if ((Model.mobile_no) != null && !(Model.mobile_no).isEmpty() && !(Model.mobile_no).equals("null") && !(Model.mobile_no).equals("")) {
                    tv_mobno.setText("Mobile no : " + Model.mobile_no);
                } else {
                    tv_mobno.setVisibility(View.GONE);
                }

                //tv_wallet.setText("Wallet : " + Model.email);

            } catch (Exception e) {
                e.printStackTrace();
            }

           /* if ((Model.have_free_credit) != null && !(Model.have_free_credit).isEmpty() && !(Model.have_free_credit).equals("null") && !(Model.have_free_credit).equals("")) {

                if ((Model.have_free_credit).equals("1")) {
                    tv_sub_ask_query.setText("(You have 1 FREE query credit)");
                } else {
                    tv_sub_ask_query.setText("Get medical advice from doctors");
                }

            } else {
                tv_sub_ask_query.setText("Get medical advice from doctors");
            }
*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   /* @Override
    public void onSliderClick(BaseSliderView slider) {
        //Toast.makeText(Lite_Home_Activity.this, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();

        try {
            String hash_val = slider.getBundle().get("extra") + "";

            Intent intent = new Intent(Lite_Home_Activity.this, QADetailNew.class);
            intent.putExtra("KEY_ctype", hash_val);
            intent.putExtra("KEY_url", hash_val);
            startActivity(intent);

            //------------ Google firebase Analitics--------------------
            Model.mFirebaseAnalytics = FirebaseAnalytics.getInstance(Lite_Home_Activity.this);
            Bundle params = new Bundle();
            params.putString("user_id", (Model.id));
            params.putString("KEY_ctype", hash_val);
            params.putString("KEY_url", hash_val);
            Model.mFirebaseAnalytics.logEvent("Force_Logout", params);
            //------------ Google firebase Analitics--------------------

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }*/


    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //------------ Bottom Bar Hide ----------------------------------------------------------------
    private void showBottomBar() {
        moveBottomBar(0);
    }

    private void hideBottomBar() {
        System.out.println("toolbar.getHeight()------" + toolbar.getHeight());
        moveBottomBar(toolbar.getHeight());
    }

    private void moveBottomBar(float toTranslationY) {

        if (ViewHelper.getTranslationY(toolbar) == toTranslationY) {
            return;
        }
        ValueAnimator animator = ValueAnimator.ofFloat(ViewHelper.getTranslationY(toolbar), toTranslationY).setDuration(400);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float translationY = (float) animation.getAnimatedValue();
                ViewHelper.setTranslationY(toolbar, translationY);
                ViewHelper.setTranslationY(toolbar, translationY);
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) toolbar.getLayoutParams();
                lp.height = (int) -translationY + getScreenHeight() - lp.topMargin;
                toolbar.requestLayout();
            }
        });
        animator.start();
    }

    //------------ Toolbar Hide ----------------------------------------------------------------
    protected int getScreenHeight() {
        return findViewById(android.R.id.content).getHeight();
    }


    class JSON_logout extends AsyncTask<JSONObject, Void, Boolean> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(Lite_Home_Activity.this);
            dialog.setMessage("Validating. Please Wait...");
            dialog.show();
            dialog.setCancelable(false);
        }

        @Override
        protected Boolean doInBackground(JSONObject... urls) {
            try {

                System.out.println("Parameters---------------" + urls[0]);

                JSONParser jParser = new JSONParser();
                logout_jsonobj = jParser.JSON_POST(urls[0], "logout");


                System.out.println("logout_jsonobj---------------" + logout_jsonobj.toString());

                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }

        protected void onPostExecute(Boolean result) {

            try {
                System.out.println("logout_jsonobj---------------" + logout_jsonobj.toString());

                dialog.cancel();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static boolean checkRingerIsOn(Context context) {
        AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        return am.getRingerMode() == AudioManager.RINGER_MODE_NORMAL;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(Lite_Home_Activity.this,
                            Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED) {
                        // Toast.makeText(Lite_Home_Activity.this, "Permission granted", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Toast.makeText(Lite_Home_Activity.this, "No Permission granted", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    private class JSON_deals_offers extends AsyncTask<String, Void, String> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(Lite_Home_Activity.this);
            dialog.setMessage("Please wait");
            dialog.show();
            dialog.setCancelable(false);

        }

        @Override
        protected String doInBackground(String... urls) {

            try {

                JSONParser jParser = new JSONParser();
                deals_offers_list = jParser.getJSONString(urls[0]);

                System.out.println("Family URL---------------" + urls[0]);
                System.out.println("deals_offers_list-------------" + deals_offers_list);

                return deals_offers_list;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String family_list) {

            try {

                JSONObject jobject_offers = new JSONObject(deals_offers_list);

                String data_text = jobject_offers.getString("data");
                String is_offer_text = jobject_offers.getString("is_offer");

                JSONArray data_text_array = new JSONArray(data_text);

                System.out.println("data_text_array------" + data_text_array.toString());

                deals_layout.removeAllViews();

                for (int i = 0; i < data_text_array.length(); i++) {
                    JSONObject jsonobj_files = data_text_array.getJSONObject(i);

                    String title_text = jsonobj_files.getString("title");
                    String bg_img_text = jsonobj_files.getString("bg_img");
                    String offers_id = jsonobj_files.getString("id");
                    String is_hline_val = jsonobj_files.getString("is_hline");
                    String btn_lbl = jsonobj_files.getString("btn_lbl");
                    String doc_id = jsonobj_files.getString("doc_id");
                    String fcode = jsonobj_files.getString("fcode");
                    String qid = jsonobj_files.getString("qid");

                    if (jsonobj_files.has("isActivePlan")) {
                        isActivePlan = jsonobj_files.getString("isActivePlan");
                    }


                    View recc_vi = getLayoutInflater().inflate(R.layout.deals_offers_row, null);

                    LinearLayout deal_full_layout = recc_vi.findViewById(R.id.deal_full_layout);
                    ImageView deal_bg = recc_vi.findViewById(R.id.deal_bg);
                    TextView tv_offers_id = recc_vi.findViewById(R.id.tv_offers_id);
                    TextView tv_join_button = recc_vi.findViewById(R.id.tv_join_button);
                    TextView tvquery = recc_vi.findViewById(R.id.tvquery);
                    TextView tv_qtype = recc_vi.findViewById(R.id.tv_qtype);
                    TextView tv_qid = recc_vi.findViewById(R.id.tv_qid);
                    TextView tv_hline = recc_vi.findViewById(R.id.tv_hline);
                    TextView tv_fcode = recc_vi.findViewById(R.id.tv_fcode);
                    TextView tv_isActivePlan = recc_vi.findViewById(R.id.tv_isActivePlan);
                    TextView tv_doc_id = recc_vi.findViewById(R.id.tv_doc_id);
                    TextView tv_offer_type = recc_vi.findViewById(R.id.tv_offer_type);

                    tv_offers_id.setText(Html.fromHtml(offers_id));
                    tv_hline.setText(Html.fromHtml(is_hline_val));
                    tvquery.setText(Html.fromHtml(title_text));
                    tv_join_button.setText(Html.fromHtml(btn_lbl));
                    tv_fcode.setText(fcode);
                    tv_isActivePlan.setText(isActivePlan);
                    tv_doc_id.setText(doc_id);
                    tv_qid.setText(qid);

                    tv_offer_type.setText(is_offer_text);

                    Picasso.with(Lite_Home_Activity.this).load(bg_img_text).placeholder(R.mipmap.thread_bg).error(R.mipmap.logo).into(deal_bg);

                    deals_layout.addView(recc_vi);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            dialog.cancel();
        }
    }

    public void deal_click(View v) {

        try {

            View parent = (View) v.getParent();
            View grand_parent = (View) parent.getParent();


            TextView tv_offers_id = grand_parent.findViewById(R.id.tv_offers_id);
            TextView tv_hline = grand_parent.findViewById(R.id.tv_hline);
            TextView tv_fcode = grand_parent.findViewById(R.id.tv_fcode);
            TextView tv_isActivePlan = grand_parent.findViewById(R.id.tv_isActivePlan);
            TextView tv_doc_id = grand_parent.findViewById(R.id.tv_doc_id);
            TextView tv_qid = grand_parent.findViewById(R.id.tv_qid);
            TextView tv_offer_type = grand_parent.findViewById(R.id.tv_offer_type);

            String offer_id = tv_offers_id.getText().toString();
            String tv_hline_text = tv_hline.getText().toString();
            String tv_fcode_text = tv_fcode.getText().toString();
            String tv_isActivePlan_text = tv_isActivePlan.getText().toString();
            String tv_doc_id_text = tv_doc_id.getText().toString();
            String tv_qid_text = tv_qid.getText().toString();
            String tv_offer_type_text = tv_offer_type.getText().toString();

            System.out.println("offer_id-----------------" + offer_id);

            System.out.println("tv_fcode_text-----------------" + tv_fcode_text);
            System.out.println("tv_isActivePlan_text-----------------" + tv_isActivePlan_text);
            System.out.println("tv_doc_id_text-----------------" + tv_doc_id_text);
            System.out.println("tv_qid_text-----------------" + tv_qid_text);
            System.out.println("tv_offer_type_text-----------------" + tv_offer_type_text);
            System.out.println("tv_hline_text-----------------" + tv_hline_text);
            System.out.println("isActivePlan-----------------" + isActivePlan);


            if (tv_isActivePlan_text.equals("0")) {
                Intent intent = new Intent(Lite_Home_Activity.this, Offers_view.class);
                intent.putExtra("offer_id", offer_id);
                startActivity(intent);
            } else {
                if (tv_hline_text.equals("1")) {
                    Intent intent = new Intent(Lite_Home_Activity.this, HotlineChatViewActivity.class);
                    intent.putExtra("selqid", tv_qid_text);
                    intent.putExtra("Doctor_id", tv_doc_id_text);
                    intent.putExtra("docurl", "");
                    intent.putExtra("fcode", tv_fcode_text);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(Lite_Home_Activity.this, Instant_Chat.class);
                    intent.putExtra("doctor_id", tv_doc_id_text);
                    intent.putExtra("plan_id", offer_id);
                    startActivity(intent);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClick_hotline(View v) {

        try {

/*            Intent intent = new Intent(CenterFabActivity.this, HotlineHome.class);
            startActivity(intent);*/

            Intent intent = new Intent(Lite_Home_Activity.this, Instant_Chat.class);
            intent.putExtra("doctor_id", "0");
            intent.putExtra("plan_id", "");
            startActivity(intent);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private class JSON_Home_dets extends AsyncTask<String, Void, String> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(Lite_Home_Activity.this);
            dialog.setMessage("Please wait");
            dialog.show();
            dialog.setCancelable(false);
        }

        @Override
        protected String doInBackground(String... urls) {

            try {

                JSONParser jParser = new JSONParser();
                home_dets_list = jParser.getJSONString(urls[0]);

                System.out.println("Family URL---------------" + urls[0]);
                System.out.println("home_dets_list-------------" + home_dets_list);

                return home_dets_list;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String family_list) {

            try {

                JSONObject jobject_offers = new JSONObject(home_dets_list);

                //--------- Getting Offer ------------------------------------
                try {

                    String name_text = jobject_offers.getString("name");
                    String email_text = jobject_offers.getString("email");
                    String mobile_text = jobject_offers.getString("mobile");
                    String wallet_text = jobject_offers.getString("wallet");

                    tv_emailid.setText("Email : " + email_text);
                    tv_mobno.setText("Mobile no : " + mobile_text);
                    tv_wallet.setText("Wallet : " + wallet_text);

                    Model.mobile_no = mobile_text;


                    String dealsAndOffers_text = jobject_offers.getString("dealsAndOffers");
                    System.out.println("dealsAndOffers_text-------- " + dealsAndOffers_text);


                    if (dealsAndOffers_text.length() > 5) {

                        offer_full_layout.setVisibility(View.VISIBLE);

                        JSONObject dealsAndOffers_obj = new JSONObject(dealsAndOffers_text);

                        String data_text = dealsAndOffers_obj.getString("data");
                        String is_offer_text = dealsAndOffers_obj.getString("is_offer");

                        JSONArray data_text_array = new JSONArray(data_text);

                        System.out.println("data_text_array------" + data_text_array.toString());

                        deals_layout.removeAllViews();

                        for (int i = 0; i < data_text_array.length(); i++) {
                            JSONObject jsonobj_files = data_text_array.getJSONObject(i);

                            String title_text = jsonobj_files.getString("title");
                            String bg_img_text = jsonobj_files.getString("bg_img");
                            String offers_id = jsonobj_files.getString("id");
                            String is_hline_val = jsonobj_files.getString("is_hline");
                            String btn_lbl = jsonobj_files.getString("btn_lbl");
                            String doc_id = jsonobj_files.getString("doc_id");
                            String fcode = jsonobj_files.getString("fcode");
                            String qid = jsonobj_files.getString("qid");

                            if (jsonobj_files.has("isActivePlan")) {
                                isActivePlan = jsonobj_files.getString("isActivePlan");
                            }

                            View recc_vi = getLayoutInflater().inflate(R.layout.deals_offers_row, null);

                            LinearLayout deal_full_layout = recc_vi.findViewById(R.id.deal_full_layout);
                            ImageView deal_bg = recc_vi.findViewById(R.id.deal_bg);
                            TextView tv_offers_id = recc_vi.findViewById(R.id.tv_offers_id);
                            TextView tv_join_button = recc_vi.findViewById(R.id.tv_join_button);
                            TextView tvquery = recc_vi.findViewById(R.id.tvquery);
                            TextView tv_qtype = recc_vi.findViewById(R.id.tv_qtype);
                            TextView tv_qid = recc_vi.findViewById(R.id.tv_qid);
                            TextView tv_hline = recc_vi.findViewById(R.id.tv_hline);
                            TextView tv_fcode = recc_vi.findViewById(R.id.tv_fcode);
                            TextView tv_isActivePlan = recc_vi.findViewById(R.id.tv_isActivePlan);
                            TextView tv_doc_id = recc_vi.findViewById(R.id.tv_doc_id);
                            TextView tv_offer_type = recc_vi.findViewById(R.id.tv_offer_type);

                            tv_offers_id.setText(Html.fromHtml(offers_id));
                            tv_hline.setText(Html.fromHtml(is_hline_val));
                            tvquery.setText(Html.fromHtml(title_text));
                            tv_join_button.setText(Html.fromHtml(btn_lbl));
                            tv_fcode.setText(fcode);
                            tv_isActivePlan.setText(isActivePlan);
                            tv_doc_id.setText(doc_id);
                            tv_qid.setText(qid);

                            tv_offer_type.setText(is_offer_text);

                            Picasso.with(Lite_Home_Activity.this).load(bg_img_text).placeholder(R.mipmap.thread_bg).error(R.mipmap.logo).into(deal_bg);

                            deals_layout.addView(recc_vi);
                        }
                    } else {
                        offer_full_layout.setVisibility(View.GONE);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                //--------- Getting  ------------------------------------

                String recent_query_text = jobject_offers.getString("recent_query");

                JSONArray recent_query_array = new JSONArray(recent_query_text);
                System.out.println("recent_query_array------" + recent_query_array.toString());
                queries_list_layout.removeAllViews();

                if (recent_query_array.length() > 0) {
                    queries_layout.setVisibility(View.VISIBLE);

                    for (int i = 0; i < recent_query_array.length(); i++) {
                        JSONObject jsonobj_files = recent_query_array.getJSONObject(i);

                        final String qid_val = jsonobj_files.getString("id");
                        String question_text = jsonobj_files.getString("question");
                        String str_status_text = jsonobj_files.getString("str_status");
                        String status_val = jsonobj_files.getString("status");
                        String datetime_text = jsonobj_files.getString("datetime");
                        final String doc_photo_url = jsonobj_files.getString("doc_photo");
                        final String is_hline_val = jsonobj_files.getString("is_hline");
                        String doc_name_text = jsonobj_files.getString("doc_name");
                        String consulted_for = jsonobj_files.getString("consulted_for");

                        View recc_vi = getLayoutInflater().inflate(R.layout.home_query_row, null);

                        LinearLayout query_full_layout = recc_vi.findViewById(R.id.query_full_layout);
                        TextView lable_bullet = recc_vi.findViewById(R.id.lable_bullet);
                        TextView tv_consulted_for = recc_vi.findViewById(R.id.tv_consulted_for);
                        TextView tvdate = recc_vi.findViewById(R.id.tvdate);
                        TextView tvquery = recc_vi.findViewById(R.id.tvquery);
                        TextView tv_qid = recc_vi.findViewById(R.id.tv_qid);
                        TextView tv_hlstatus = recc_vi.findViewById(R.id.tv_hlstatus);
                        TextView tv_docurl = recc_vi.findViewById(R.id.tv_docurl);
                        CircleImageView imageview_poster = recc_vi.findViewById(R.id.imageview_poster);
                        TextView tv_doctname = recc_vi.findViewById(R.id.tv_doctname);
                        TextView tv_qtype = recc_vi.findViewById(R.id.tv_qtype);

                        lable_bullet.setBackgroundColor(getResources().getColor(R.color.green_800));
                        tv_consulted_for.setText(consulted_for);
                        tvdate.setText(datetime_text);
                        tvquery.setText(question_text);
                        tv_qid.setText(qid_val);
                        tv_hlstatus.setText(status_val);
                        tv_doctname.setText(doc_name_text);
                        tv_docurl.setText(doc_photo_url);
                        tv_qtype.setText(str_status_text);

                        Picasso.with(Lite_Home_Activity.this).load(doc_photo_url).placeholder(R.mipmap.thread_bg).error(R.mipmap.logo).into(imageview_poster);

                        query_full_layout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (is_hline_val.equals("1")) {
                                    Intent i = new Intent(Lite_Home_Activity.this, HotlineChatViewActivity.class);
                                    i.putExtra("Doctor_id", "");
                                    i.putExtra("selqid", qid_val);
                                    i.putExtra("docurl", doc_photo_url);
                                    startActivity(i);
                                    overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
                                } else {
                                    Intent i = new Intent(Lite_Home_Activity.this, QueryViewActivity.class);
                                    i.putExtra("qtype", "followup");
                                    i.putExtra("qid", qid_val);
                                    startActivity(i);
                                    overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
                                }
                            }
                        });

                        queries_list_layout.addView(recc_vi);
                    }
                } else {
                    queries_layout.setVisibility(View.GONE);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            dialog.cancel();
        }
    }

    private class JSON_FCM_Regid extends AsyncTask<String, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(String... urls) {

            try {
                str_response = new JSONParser().getJSONString(urls[0]);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }

        protected void onPostExecute(Boolean result) {
            System.out.println("str_response--------------" + str_response);
        }
    }


}

