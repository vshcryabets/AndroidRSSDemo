package com.v2soft.rssdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.v2soft.rssdemo.fragments.FeedListFragment;

/**
 * Main activity.
 * @author V.Shcryabets<vshcryabets@gmail.com>
 *
 */
public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            final FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction();
            final Fragment fragment = FeedListFragment.newInstance();
            transaction.replace(R.id.fragmentContainer, fragment);
            transaction.commit();
        }
    }
}
