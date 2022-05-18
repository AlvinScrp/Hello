package com.a.viewpage;

import android.os.Bundle;

import com.a.viewpage.ui.main.MyDialogFragment;
import com.a.viewpage.ui.main.MyTextView;
import com.a.viewpage.ui.main.PlaceholderFragment;
import com.a.viewpage.ui.main.PlaceholderFragment2;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.a.viewpage.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page);

        Log.d("alvin-app","ViewPageActivity: onCreate");


        @StringRes final int[] TAB_TITLES = new int[]{R.string.tab_text_0, R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3, R.string.tab_text_4};
        List<Fragment> fragmentList = new ArrayList<>();
        for (int i = 0; i < TAB_TITLES.length; i++) {
            fragmentList.add(PlaceholderFragment2.newInstance(i));
//            fragmentList.add(PlaceholderFragment.newInstance(i));
        }
        for (Fragment fragment : fragmentList) {

            fragment.getLifecycle().addObserver(new LifecycleEventObserver() {
                @Override
                public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                    Log.d("alvin", "PlaceholderFragment2 " + ((PlaceholderFragment2) source).index + " onStateChanged:" + event.name());
                    PlaceholderFragment2 fragment = (PlaceholderFragment2) source;
                    if (event.equals(Lifecycle.Event.ON_RESUME)) {
                        changeFocusManual(fragment.getView(), true);
                    } else if (event.equals(Lifecycle.Event.ON_PAUSE)) {
                        changeFocusManual(fragment.getView(), false);
                    }
                }
            });
//            fragment.getViewLifecycleOwner().getLifecycle().addObserver(new LifecycleEventObserver() {
//                @Override
//                public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
//                    Log.d("alvin", "PlaceholderFragment " + ((PlaceholderFragment) source).index + "ViewLifecycleOwner onStateChanged:" + event.name());
//                }
//            });
        }
        new Handler().postDelayed(() -> {
            changeFocusManual(fragmentList.get(0).getView(), true);
        }, 500);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), TAB_TITLES, fragmentList);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.setOffscreenPageLimit(2);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);


        fab.setOnClickListener(view -> {
            MyDialogFragment dialogFragment = new MyDialogFragment();
            dialogFragment.showNow(getSupportFragmentManager(), "myDialogFragment");
        });

        Handler handler = new Handler();
    }

    private void changeFocusManual(View view, boolean hasFocus) {
        if (view instanceof MyTextView) {
            ((MyTextView) view).changeFocusManual(hasFocus);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int count = viewGroup.getChildCount();
            for (int i = 0; i < count; i++) {
                changeFocusManual(viewGroup.getChildAt(i), hasFocus);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("alvin-app","ViewPageActivity: onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("alvin-app","ViewPageActivity: onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("alvin-app","ViewPageActivity: onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("alvin-app","ViewPageActivity: onStop");
    }



}