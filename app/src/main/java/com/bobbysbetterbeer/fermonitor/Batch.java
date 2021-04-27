package com.bobbysbetterbeer.fermonitor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class Batch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.batch_menu);
    }

    public void start_batch(View view) {
        String _grav_id_str = "" + ((EditText) findViewById(R.id.batch_grav_id)).getText();
        int _grav_id = -1;
        if(_grav_id_str.length() > 0)
            _grav_id = Integer.parseInt(_grav_id_str);
        int _carboy_id = Integer.parseInt("" + ((EditText) findViewById(R.id.batch_carboy_id)).getText());
        int _temp = Integer.parseInt("" + ((EditText) findViewById(R.id.batch_temp)).getText());
        String _name = "" + ((EditText) findViewById(R.id.batch_name)).getText();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("app_key", "" + Variables.APP_KEY.get());
            if(_grav_id > -1)
                jsonObject.put("grav_id", _grav_id);
            jsonObject.put("carboy_id", _carboy_id);
            jsonObject.put("target_temp", _temp);
            jsonObject.put("batch_name", _name);

            new Thread(new ServerStuff("/carboy/" + _carboy_id + "/start_batch", jsonObject)).start();
        } catch (JSONException e) {}
    }

    public void stop_batch(View view) {
        int _carboy_id = Integer.parseInt("" + ((EditText) findViewById(R.id.batch_stop_carboy_id)).getText());
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("app_key", "" + Variables.APP_KEY.get());
            jsonObject.put("_id", _carboy_id);

            new Thread(new ServerStuff("/carboy/" + _carboy_id + "/stop_batch", jsonObject)).start();
        } catch (JSONException e) {}
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Main.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
