package com.example.mobile.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal
import java.net.URL

@Entity
@Parcelize
data class Produto (
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val nome: String,
    val descricao: String,
    val valor: BigDecimal,
    val disponivel: String,
    val imagem: String? = null

):Parcelable
