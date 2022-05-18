package com.a.viewpage.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.a.viewpage.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {


    public int index = 0;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        fragment.index = index;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Log.d("alvin", "fragment:" + index + ",onCreate");
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_view_page, container, false);
        final TextView textView = root.findViewById(R.id.section_label);
        textView.setText("tab " + index);
        final MyView myView = root.findViewById(R.id.myview);
//        Log.d("alvin", "fragment:" + index + ",onCreateView");
        myView.setText("tab " + index);
        myView.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), SubActivity.class));
        });

        MyTextView bottomText = root.findViewById(R.id.bottomText);
        bottomText.updateText("bottomText tab " + index);
//        bottomText.getTag("sd")

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                FrameLayout framelayout = getView().findViewById(R.id.framelayout);
//                MyTextView tv = new MyTextView(getContext());
//                tv.updateText("tab " + index);
//                framelayout.addView(tv);
//            }
//        }, 2000);


        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
//        Log.d("alvin", "fragment:" + index + ",onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
//        Log.d("alvin", "fragment:" + index + ",onPause");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        Log.d("alvin", "fragment:" + index + ",onDestroyView");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
//        Log.d("alvin", "fragment:" + index + ",onHiddenChanged:" + hidden);
    }

}