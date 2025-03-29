package com.example.mobile.ui.activity

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile.R
import com.example.mobile.model.Produto
import com.example.mobile.ui.recyclerview.adapter.ListaProdutosAdapter
import java.math.BigDecimal

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*val nome = findViewById<TextView>(R.id.recyclerView)
        nome.text = "Cesta de frutas"
        val descricao = findViewById<TextView>(R.id.descricao)
        descricao.text = "Laranja, manga e maçã"
        val valor = findViewById<TextView>(R.id.valor)
        valor.text = "19.99"*/
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = ListaProdutosAdapter(context = this, produtos = listOf(
            Produto(nome = "Maça", descricao = "Maça Saborosa", valor = BigDecimal("19.99")),
            Produto(nome = "Laranja", descricao = "Laranja Saborosa", valor = BigDecimal("19.99")),
            Produto(nome = "Laranja", descricao = "Laranja Saborosa", valor = BigDecimal("19.99")),
            Produto(nome = "Laranja", descricao = "Laranja Saborosa", valor = BigDecimal("19.99")),
            Produto(nome = "Laranja", descricao = "Laranja Saborosa", valor = BigDecimal("19.99")),
            Produto(nome = "Laranja", descricao = "Laranja Saborosa", valor = BigDecimal("19.99"))
        ))
       // recyclerView.layoutManager = LinearLayoutManager(this)
    }

}

