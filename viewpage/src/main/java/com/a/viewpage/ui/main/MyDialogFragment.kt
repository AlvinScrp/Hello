package com.a.viewpage.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.a.viewpage.R

class MyDialogFragment  : DialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //确定Dialog布局
        return inflater.inflate(R.layout.layout_my_dialog, container, false)
    }
}