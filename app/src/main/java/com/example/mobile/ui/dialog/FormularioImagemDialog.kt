package com.example.mobile.ui.dialog

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.example.mobile.databinding.FormularioImagemBinding
import com.example.mobile.extensions.tentaCarregarImagem

class FormularioImagemDialog(private val context: Context) {

    fun mostra(
        urlPadrao: String? = null,
        quandoImagemCarregada: (imagem: String) -> Unit
    ){
        FormularioImagemBinding.inflate(LayoutInflater.from(context)).apply {
            urlPadrao?.let {
                formularioImagemImageView.tentaCarregarImagem(it)
                formularioImagemUrl.setText(it)
            }

            formularioImagemBotaoCarregar.setOnClickListener{
                val url = formularioImagemUrl.text.toString()
                formularioImagemImageView.tentaCarregarImagem(url)
            }

            AlertDialog.Builder(context)
                .setView(root)
                .setPositiveButton("OK") { dialog, _ ->
                    val url = formularioImagemUrl.text.toString()
                    Log.i("FormularioImagemDialog", "mostra: $url")
                    quandoImagemCarregada(url)
                }
                .setNegativeButton("Cancelar") {dialog, _ -> dialog.dismiss()}
                .show()
        }
    }
}