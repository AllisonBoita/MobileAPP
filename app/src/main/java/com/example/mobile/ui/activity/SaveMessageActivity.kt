package com.example.mobile.ui.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile.R

class SaveMessageActivity : AppCompatActivity(R.layout.activity_lista_produtos ){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produto_salvo)

        Handler(Looper.getMainLooper()).postDelayed({
            finish()
        }, 2000)
    }
}