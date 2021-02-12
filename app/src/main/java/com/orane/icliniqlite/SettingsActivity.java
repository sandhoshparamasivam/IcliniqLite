package com.orane.icliniqlite;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.orane.icliniqlite.Model.Model;
import com.orane.icliniqlite.network.JSONParser;
import com.orane.icliniqlite.network.ShareIntent;

import org.json.JSONObject;

import me.drakeet.materialdialog.MaterialDialog;

public class SettingsActivity extends AppCompatActivity {

    LinearLayout signout_layout, support_layout, about_app_layout, mybooking_layout, myvideos_layout, terms_layout, profile_layout, policy_layout, reportissue_layout, rate_layout, share_layout, aredoctor_layout, pv_consult_layout;
    RelativeLayout user_layout;
    TextView tv_emailid, tv_pname;

    SharedPreferences sharedpreferences;
    public static final String noti_status = "noti_status_key";
    public static final String noti_sound = "noti_sound_key";
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Login_Status = "Login_Status_key";
    public static final String app_language = "app_language_key";
    public static final String mobile_no = "mobile_no_key";
    public static final String user_name = "user_name_key";
    public static final String password = "password_key";
    public static final String Name = "Name_key";
    public static final String bcountry = "bcountry_key";
    public static final String photo_url = "photo_url";
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


    JSONObject logout_json_validate, logout_jsonobj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        //------------ Object Creations -------------------------------
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Settings");

            TextView mTitle = toolbar.findViewById(R.id.toolbar_title);
            Typeface khandBold = Typeface.createFromAsset(getApplicationContext().getAssets(), Model.font_name_bold);
            mTitle.setTypeface(khandBold);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.app_color2));
        }

        //-------- Initialization -----------------------------------------------------
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        Model.token = sharedpreferences.getString(token, "");
        Model.browser_country = sharedpreferences.getString(browser_country, "");
        Model.email = sharedpreferences.getString(email, "");
        Model.name = sharedpreferences.getString(Name, "");

        System.out.println("MModel.name---------------------" + Model.name);
        System.out.println("Model.email------------------" + Model.email);
        System.out.println("Model.token------------------" + Model.token);

        Model.first_time = "Yes";
        //-------- Initialization -----------------------------------------------------


        signout_layout = findViewById(R.id.signout_layout);
        user_layout = findViewById(R.id.user_layout);
        rate_layout = findViewById(R.id.rate_layout);
        share_layout = findViewById(R.id.share_layout);
        terms_layout = findViewById(R.id.terms_layout);
        policy_layout = findViewById(R.id.policy_layout);
        terms_layout = findViewById(R.id.terms_layout);
        reportissue_layout = findViewById(R.id.reportissue_layout);
        about_app_layout = findViewById(R.id.about_app_layout);
        support_layout = findViewById(R.id.support_layout);
        tv_pname = findViewById(R.id.tv_pname);
        tv_emailid = findViewById(R.id.tv_emailid);

        tv_pname.setText(Model.name);
        tv_emailid.setText(Model.email);


        //================ Initialize ======================---------------
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        signout_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ask_logout();
            }
        });

        user_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("User Account Details.............");
                Intent intent = new Intent(SettingsActivity.this, Patient_Profile_Activity.class);
                startActivity(intent);

            }
        });
        rate_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://play.google.com/store/apps/details?id=com.orane.icliniqlite";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        share_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ShareIntent sintent = new ShareIntent();
                    sintent.ShareApp(SettingsActivity.this, "MainActivity");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        terms_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(SettingsActivity.this, WebViewActivity.class);
                i.putExtra("url", "https://www.icliniq.com/p/terms?nolayout=1");
                i.putExtra("type", "Terms");
                startActivity(i);
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

            }
        });

        policy_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(SettingsActivity.this, WebViewActivity.class);
                i.putExtra("url", "https://www.icliniq.com/p/privacy?nolayout=1");
                i.putExtra("type", "Privacy Policy");
                startActivity(i);
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            }
        });

        reportissue_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SettingsActivity.this, CommonActivity.class);
                intent.putExtra("type", "feedback");
                startActivity(intent);
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

            }
        });

        about_app_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SettingsActivity.this, CommonActivity.class);
                intent.putExtra("type", "aboutus");
                startActivity(intent);

            }
        });

        support_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SettingsActivity.this, CommonActivity.class);
                intent.putExtra("type", "support");
                startActivity(intent);

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void ask_logout() {

        final MaterialDialog alert = new MaterialDialog(SettingsActivity.this);
        alert.setTitle("Logout.!");
        alert.setMessage("Are you sure you want to logout?");
        alert.setCanceledOnTouchOutside(false);
        alert.setPositiveButton("Yes", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //============================================================
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(Login_Status, "0");
                editor.apply();
                //============================================================


                //-------------- Logout-------------------------------------------------
                try {

                    logout_json_validate = new JSONObject();
                    logout_json_validate.put("user_id", Model.id);
                    logout_json_validate.put("reg_id", Model.device_token);
                    logout_json_validate.put("os_type", "1");
                    System.out.println("logout_json_validate----" + logout_json_validate.toString());
                    new JSON_logout().execute(logout_json_validate);

                    //--------------------------------------------------
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //--------------- Logout------------------------------------------------


            }
        });


        alert.setNegativeButton("No", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });

        alert.show();
    }


    class JSON_logout extends AsyncTask<JSONObject, Void, Boolean> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(SettingsActivity.this);
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

                finishAffinity();
                Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        try {
            tv_pname.setText(Model.name);
            tv_emailid.setText(Model.email);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
