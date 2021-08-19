package com.a.glide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.TextureView
import com.a.glide.databinding.ActivityMainBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var url = "https://staticimg.ngmm365.com/c21c45a4b90dd1bf0f8074c8a07e0539-w900_h897.png"

        Glide.with(this).load(url).into(binding.image)

        Glide.with(this).load(url).centerCrop().transition(DrawableTransitionOptions.withCrossFade()).into(binding.image2)

       var view: TextureView? = null

    }
}