package com.bobbysbetterbeer.fermonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main extends AppCompatActivity {

    private Button buttons[] = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        new Variables();

        buttons = new Button[]{findViewById(R.id.button1), findViewById(R.id.button2), findViewById(R.id.button3)};

        if(!Variables.HAS_SET_IP.get())
            for(int i = 0; i < 3; i++)
                buttons[i].setEnabled(false);
        else
            ((EditText) findViewById(R.id.rpi_ip_address)).setText("" + Variables.IP_ADDRESS.get());
    }

    public void grav(View view) {
        Intent intent = new Intent(this, Grav.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void carboy(View view) {
        Intent intent = new Intent(this, Carboy.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void batch(View view) {
        Intent intent = new Intent(this, Batch.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void setIP(View view) {
        EditText editText = (EditText) findViewById(R.id.rpi_ip_address);
        Variables.IP_ADDRESS.set("" + editText.getText());

        Variables.HAS_SET_IP.set(true);
        for(int i = 0; i < 3; i++)
            buttons[i].setEnabled(true);
    }

    @Override
    public void onBackPressed() {
        this.finishAffinity();
        System.exit(0);
    }
}