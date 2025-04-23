package com.example.mobile.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
data class Produto (
    val nome: String,
    val descricao: String,
    val valor: BigDecimal,
    val disponivel: String,
    val imagem: String? = null

):Parcelable
