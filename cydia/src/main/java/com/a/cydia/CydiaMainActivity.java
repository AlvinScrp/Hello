package com.a.cydia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class CydiaMainActivity extends AppCompatActivity {

    static {
//        System.loadLibrary("hellohook.cy");
        System.loadLibrary("dexhook.cy");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cydia_main2);

        // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);
        tv.setText(M.a());
    }

//    /**
//     * A native method that is implemented by the 'native-lib' native library,
//     * which is packaged with this application.
//     */
//    public native String stringFromJNI();
}