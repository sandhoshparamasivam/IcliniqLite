package com.orane.icliniqlite.Model;

import com.google.firebase.analytics.FirebaseAnalytics;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;

public class Model {

    public static String App_ver = "1.0.10";
    public static String App_ver_slno = "11";
    public static String App_Rel = "11-February-2021";

    //public static String font_name = "fonts/NotoSans-Regular.ttf";
    public static String font_name = "fonts/OpenSans-Regular.ttf";
    public static String font_name_italic = "fonts/OpenSans-Italic.ttf";
    public static String font_name_light = "fonts/OpenSans-Light.ttf";
    public static String font_name_bold_200 = "fonts/OpenSans-Bold.ttf";
    public static String font_name_bold = "fonts/OpenSans-SemiBold.ttf";

    public static String BASE_URL = "https://www.icliniq.com/";
//    public static String BASE_URL = "https://pentest.icliniq.com/";
    //public static String BASE_URL = "http://54.158.193.113/";
    //public static String BASE_URL = "http://192.168.0.132/projects/icliniq/web/index.php/";
    //public static String BASE_URL = "http://192.168.0.112/projects/icliniq/web/index.php/";
    //public static String BASE_URL="https://icliniq.cloudapp.net/index.php/";
    //public static Bitmap.Config config = new Bitmap.Config("icliniq", "HhEvRrQ9Qdar_mkHVXo_Qg");
    //http://icliniq.cloudapp.net/index.php

    public static FirebaseAnalytics mFirebaseAnalytics;

    public static String clinics, terms_isagree, device_token, vendor_name, agency_name, agency_val, doc_lang,
            treatment_skills, edit_query, doc_photo, doc_name, doc_sp, appt_id, meeting_id, docurl, screen_status, fcode, ftrack_show, cons_phno, sel_spec_code, query, speciality, photo_url, from, return_qid, local_file_url, mcode, mnum, str_status, first_query,
            mobvalidate, ftrack_fee, login_status, kmid, isValid, password, name, id, status, browser_country, email, fee_q, fee_consult, fee_q_inr, fee_consult_inr, currency_symbol, currency_label, have_free_credit;
    public static String qid = "";
    public static String dqid = "";
    public static String app_lang = "";
    public static String query_id = "";
    public static File imageFile;
    public static int mNotificationCounter = 5;
    public static String query_reponse, first_time;
    public static String mobile_no, family_list, fee_cp, edt_search_text_val, disease_type_text, fee_hp, fee_lp, cons_type, country_code, pat_country, selected_qid, push_qid, push_msg, push_type, push_title, select_specname, select_spec_val, ftrack_str_status, invlabel, inv_curr, querystatus, upload_files = "";
    public static String ftrack_mode, dashboard = "";
    public static String consult_id, open_url, query_cache, query_launch = "";
    public static String refcode, token, has_free_follow, spec_name, spec_code, cons_timezone_name, navi_next = "";
    public static String fule_full_path, sel_country_name, sel_country_code, cons_lang_code, cons_timezone_val, cons_select_date = "";
    public static String sel_timerange_code, cons_query, time_range, cons_date, cons_lang, cons_ccode, cons_number, cons_timezone;

    public static String prep_inv_id, prep_inv_fee, prep_inv_strfee, txn_id, askdrafttext, doctor_id, compmore, prevhist, curmedi, pastmedi, labtest;
    public static String attach_qid, attach_status, attach_file_url, attach_filename, attach_id, inv_title, inv_desc, inv_fee, inv_walletfee, inv_btnconfirm, inv_browsercountry, invfeetot;
    public static JSONArray mydoctor, hotlinedocs, myconsultation, mybooking, myquery_aaray, doctor_aaray;
    public static JSONObject getTimeZone, prepaid_pack_json;

    public static int update_alert_days = 2;

    //--------- Google_analytics---------------------------------------------------
    public static String razor_pay_public_key = "rzp_live_rqj0TdNTXlZUhQ";
    //--------- Google_analytics---------------------------------------------------
}
