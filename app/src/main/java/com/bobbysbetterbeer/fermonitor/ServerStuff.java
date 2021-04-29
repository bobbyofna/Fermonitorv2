package com.bobbysbetterbeer.fermonitor;

import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONObject;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class ServerStuff extends Thread {

    private ASyncSend aSyncSend = null;

    public ServerStuff(String _endpoint, JSONObject _json) {
        aSyncSend = new ASyncSend(_endpoint, _json);
    }

    @Override
    public void run() {
        aSyncSend.execute();
    }

    public void updateUI() { /*
        Variables.CURRENT_ACTIVITY.get().runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        }); */
    }

    private class ASyncSend extends AsyncTask<Void, Void, Void> {

        private String endpoint = null;
        private JSONObject json = null;

        public ASyncSend(String _endpoint, JSONObject _json) {
            endpoint = _endpoint;
            json = _json;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            updateUI();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                URL url = new URL("http://" + Variables.IP_ADDRESS.get() + endpoint);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);
                conn.setConnectTimeout(15000);
                conn.connect();

                OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
                writer.write(json.toString());
                writer.flush();
                writer.close();

                int response = conn.getResponseCode();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Attempting To Login\n");
                stringBuilder.append("Url:  " + url.toString() + "\n");
                stringBuilder.append("Code: " + response + "\n");
                stringBuilder.append("Msg:  " + conn.getResponseMessage() + "\n");

                conn.disconnect();
            } catch (java.net.SocketTimeoutException e) {
                Log.e("ERROR", "" + Log.getStackTraceString(e));
            } catch (Exception e) {
                Log.e("ERROR", "" + Log.getStackTraceString(e));
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void avoid) {
            super.onPostExecute(avoid);
            updateUI();
        }
    }
}
