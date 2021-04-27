package com.bobbysbetterbeer.fermonitor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class Carboy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carboy_menu);
    }

    public void create(View view) {
        int _id = Integer.parseInt("" + ((EditText) findViewById(R.id.create_carboy_id)).getText());
        String _name = "" + ((EditText) findViewById(R.id.create_carboy_name)).getText();
        String _unique = "" + ((EditText) findViewById(R.id.create_carboy_unique)).getText();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("app_key", "" + Variables.APP_KEY.get());
            jsonObject.put("_id", _id);
            jsonObject.put("name", _name);
            jsonObject.put("carboy_key", _unique);

            new Thread(new ServerStuff("/carboy/create", jsonObject)).start();
        } catch (JSONException e) {}
    }

    public void update(View view) {
        int _id = Integer.parseInt("" + ((EditText) findViewById(R.id.update_carboy_id)).getText());
        String _ip = "" + ((EditText) findViewById(R.id.update_carboy_ip)).getText();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("app_key", "" + Variables.APP_KEY.get());
            jsonObject.put("_id", _id);
            jsonObject.put("_ip", _ip);

            new Thread(new ServerStuff("/carboy/" + _id + "/update/ip", jsonObject)).start();
        } catch (JSONException e) {}
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Main.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
