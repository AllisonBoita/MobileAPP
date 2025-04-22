package com.example.mobile.extensions

import android.widget.ImageView
import coil.load
import com.example.mobile.R

fun ImageView.tentaCarregarImagem(url: String? = null){
    load(url) {
        fallback(R.drawable.imagem_padrao) // aparece se for null ou blank
        error(R.drawable.erro)    // aparece se der erro no carregamento
        placeholder(R.drawable.placeholder)
    }
}