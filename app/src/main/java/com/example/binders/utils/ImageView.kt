package com.example.binders.utils

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Base64
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

fun ImageView.load(url: String?, errorImage: Int? = null,
                   onFinish: ((isSuccess: Boolean) -> Unit)? = null, strategy: DiskCacheStrategy = DiskCacheStrategy.NONE ) {
    if (url?.isBlank() == true) return
    val glide = Glide
            .with(context)
            .load(url)
            .setupOptions(width, height)
            .diskCacheStrategy(strategy)
    when {
        errorImage != null -> {
            glide.error(errorImage)
            glide.into(this).waitForLayout()
        }
        onFinish != null -> {
            glide.into(this, onFinish)
        }
        else -> {
            glide.into(this).waitForLayout()
        }
    }
}

fun ImageView.load(uri: Uri?, errorImage: Int? = null, onFinish: ((isSuccess: Boolean) -> Unit)? = null) {
    if (uri == tag) return
    val glide = Glide.with(context).load(uri).setupOptions(width, height)
    when {
        errorImage != null -> {
            glide.error(errorImage)
            glide.into(this).waitForLayout()
        }
        onFinish != null -> {
            glide.into(this, onFinish)
        }
        else -> {
            glide.into(this).waitForLayout()
        }
    }
    tag = uri
}

fun ImageView.load(res: Int, errorImage: Int? = null, onFinish: ((isSuccess: Boolean) -> Unit)? = null) {
    if (res == tag) return
    val glide = Glide.with(context).load(res).setupOptions(width, height)
    when {
        errorImage != null -> {
            glide.error(errorImage)
            glide.into(this).waitForLayout()
        }
        onFinish != null -> {
            glide.into(this, onFinish)
        }
        else -> {
            glide.into(this).waitForLayout()
        }
    }

    tag = res
}

fun ImageView.loadBase64(base64ImageString: String?, errorImage: Int? = null, strategy: DiskCacheStrategy = DiskCacheStrategy.NONE ) {
    if (base64ImageString?.isBlank() == true) return
    val glide = Glide
            .with(context)
            .asBitmap()
            .load(Base64.decode(base64ImageString, Base64.DEFAULT))
            .diskCacheStrategy(strategy)
    when {
        errorImage != null -> {
            glide.error(errorImage)
            glide.into(this).waitForLayout()
        }
        else -> {
            glide.into(this).waitForLayout()
        }
    }
}

private fun RequestBuilder<Drawable>.setupOptions(width: Int, height: Int) =
        override(width, height)
                .thumbnail(0.25f)
                .centerCrop()
                .transform(RoundedCorners(8))
                .diskCacheStrategy(DiskCacheStrategy.ALL)


@SuppressLint("CheckResult")
private fun RequestBuilder<Drawable>.into(img: ImageView, onFinish: (isSuccess: Boolean) -> Unit) {
    object : RequestListener<Drawable> {
        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
            onFinish(false)
            return false
        }

        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
            onFinish(true)
            return true
        }
    }
}