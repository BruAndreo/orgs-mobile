package com.example.orgs.extensions

import android.widget.ImageView
import coil.load
import com.example.orgs.R

fun ImageView.loadImage(url: String? = null) {
    load(url) {
        placeholder(R.drawable.placeholder)
        fallback(R.drawable.imagem_padrao)
        error(R.drawable.imagem_padrao)
    }
}