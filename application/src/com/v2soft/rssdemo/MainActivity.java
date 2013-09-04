package com.v2soft.rssdemo;

import com.v2soft.rssdemo.services.RefreshFeedService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, RefreshFeedService.class);
        startService(intent);
    }
}
