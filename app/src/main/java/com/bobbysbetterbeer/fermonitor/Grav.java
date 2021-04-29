package com.bobbysbetterbeer.fermonitor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class Grav extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grav_menu);
    }

    public void create(View view) {
        int _id = Integer.parseInt("" + ((EditText) findViewById(R.id.create_grav_id)).getText());
        String _name = "" + ((EditText) findViewById(R.id.create_grav_name)).getText();
        String _unique = "" + ((EditText) findViewById(R.id.create_grav_unique)).getText();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("app_key", "" + Variables.APP_KEY.get());
            jsonObject.put("_id", _id);
            jsonObject.put("name", _name);
            jsonObject.put("grav_key", _unique);

            new Thread(new ServerStuff("/grav/create", jsonObject)).start();
        } catch (JSONException e) {}
    }

    public void calibrate(View view) {
        int _id = Integer.parseInt("" + ((EditText) findViewById(R.id.cal_grav_id)).getText());
        int _index = Integer.parseInt("" + ((EditText) findViewById(R.id.cal_grav_index)).getText());
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("app_key", "" + Variables.APP_KEY.get());
            jsonObject.put("_id", _id);

            new Thread(new ServerStuff("/grav/" + _id + "/calibrate/" + _index + "", jsonObject)).start();
        } catch (JSONException e) {}
    }

    public void delete(View view) {
        int _id = Integer.parseInt("" + ((EditText) findViewById(R.id.cal_grav_id)).getText());
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("app_key", "" + Variables.APP_KEY.get());
            jsonObject.put("_id", _id);

            new Thread(new ServerStuff("/grav/" + _id + "/delete", jsonObject)).start();
        } catch (JSONException e) {}
    }

    public void print_gravs(View view) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("app_key", "" + Variables.APP_KEY.get());
            new Thread(new ServerStuff("/grav/print", jsonObject)).start();
        } catch (JSONException e) {}
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Main.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
