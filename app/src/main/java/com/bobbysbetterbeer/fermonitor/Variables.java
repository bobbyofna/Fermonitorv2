package com.bobbysbetterbeer.fermonitor;

import android.app.Activity;

import java.util.concurrent.atomic.AtomicReference;

public class Variables {

    public static final AtomicReference<String> IP_ADDRESS = new AtomicReference<>();
    public static final AtomicReference<Boolean> HAS_SET_IP = new AtomicReference<>(false);
    public static final AtomicReference<Activity> CURRENT_ACTIVITY = new AtomicReference<>();
    public static final AtomicReference<String> APP_KEY = new AtomicReference<>("HGgHBlgKJHVkjydIOUoutwUOITghcHVB");

    public Variables() {}
}
