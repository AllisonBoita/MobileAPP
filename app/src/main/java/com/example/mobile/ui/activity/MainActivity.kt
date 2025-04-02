package com.example.mobile.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile.R
import com.example.mobile.dao.ProdutosDao
import com.example.mobile.model.Produto
import com.example.mobile.ui.recyclerview.adapter.ListaProdutosAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.math.BigDecimal


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*val nome = findViewById<TextView>(R.id.recyclerView)
        nome.text = "Cesta de frutas"
        val descricao = findViewById<TextView>(R.id.descricao)
        descricao.text = "Laranja, manga e maçã"
        val valor = findViewById<TextView>(R.id.valor)
        valor.text = "19.99"*/

        // recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val dao = ProdutosDao()
        Log.i("MainActivity", "onCreate: ${dao.buscaTodos()} ")
        recyclerView.adapter = ListaProdutosAdapter(context = this, produtos = dao.buscaTodos())
        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        fab.setOnClickListener {
            val intent = Intent(this, FormProdutoActivity::class.java)
            startActivity(intent)

        }
    }

}

