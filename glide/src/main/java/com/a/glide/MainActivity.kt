package com.a.glide

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.TextureView
import android.widget.ImageView
import com.a.glide.databinding.ActivityMainBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var  url1 = "https://cdn.webuy.ai/others/assets/img/2022/06/07/8f11576d-d3ce-486a-95df-9f1e068ffc66____size702x200.png"
//        url1 = "https://cdn.webuy.ai/others/assets/img/2022/06/07/8f11576d-d3ce-486a-95df-9f1e068ffc66____size702x200.png?x-oss-process=image/resize,w_1011/format,webp"
        var url2 = "https://cdn.webuy.ai/others/assets/img/2022/06/07/7b4508d3-5921-4206-b0bd-c350b2a67fa0____size300x169.png"
//         url2 = "https://cdn.webuy.ai/others/assets/img/2022/06/07/7b4508d3-5921-4206-b0bd-c350b2a67fa0____size300x169.png?x-oss-process=image/resize,w_1011/format,webp"
        binding.btnLoadImage.setOnClickListener {
            loadImage(url1,binding.image)
            loadImage(url2,binding.image2)
        }
//        Glide.with(this).load(url).into(binding.image)

//        Glide.with(this).load(url).centerCrop().transition(DrawableTransitionOptions.withCrossFade()).into(binding.image2)



    }
    private fun loadImage(url: String, imageView: ImageView) {
        Glide.with(imageView)
            .load(url)
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .listener(listener)
            .into(imageView)
    }

    private var listener = object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            e?.printStackTrace()
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            return false
        }
    }

}