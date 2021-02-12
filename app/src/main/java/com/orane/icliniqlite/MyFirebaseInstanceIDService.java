package com.orane.icliniqlite;

import android.os.AsyncTask;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.orane.icliniqlite.Model.Model;
import com.orane.icliniqlite.network.JSONParser;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseIIDService";
    String str_response;

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        System.out.println("Token-----------------------" + refreshedToken);

        Log.d(TAG, "Refreshed token: " + refreshedToken);

        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String token) {
        System.out.println("Latest getting Token----------" + token);

        Model.device_token = token;

        //-------------------------------------------------------------------
        String fcm_url = Model.BASE_URL + "sapp/updateDeviceRegId?reg_id=" + token + "&user_id=" + (Model.id) + "&v=" + Model.App_ver_slno + "&token=" + Model.token;
        System.out.println("fcm_url---------" + fcm_url);
        new JSON_FCM_Regid().execute(fcm_url);
        //-------------------------------------------------------------------
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