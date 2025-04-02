package com.example.mobile.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.mobile.R
import com.example.mobile.dao.ProdutosDao
import com.example.mobile.model.Produto
import java.math.BigDecimal

class FormProdutoActivity : AppCompatActivity(R.layout.activity_form_produto) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val botaoSalvar = findViewById<Button>(R.id.botao_salvar)
        botaoSalvar.setOnClickListener {
            val campoNome = findViewById<EditText>(R.id.nome)
            val nome = campoNome.text.toString()
            Log.i("FormularioProduto", "onCreate: $nome")
            val campoDescricao = findViewById<EditText>(R.id.descricao)
            val descricao = campoDescricao.text.toString()
            Log.i("FormularioProduto", "onCreate: $descricao")
            val campoValor = findViewById<EditText>(R.id.valor)
            val valorEmTexto = campoValor.text.toString()
            val valor =  if (valorEmTexto.isBlank()) {
                BigDecimal.ZERO
            } else {
                BigDecimal(valorEmTexto)
            }
            Log.i("FormularioProduto", "onCreate: $valor")
            val campoDisponivel = findViewById<EditText>(R.id.disponivel)
            val disponivel = campoDisponivel.text.toString()
            Log.i("FormularioProduto", "onCreate: $disponivel")

            val novoProduto = Produto(
                nome = nome,
                descricao = descricao,
                valor = valor,
                disponivel = disponivel
            )

            Log.i("FormularioProduto","onCreate: $novoProduto")

            val dao = ProdutosDao()
            dao.adiciona(novoProduto)

            Log.i("FormularioProduto","onCreate: ${dao.buscaTodos()}")
            val intent = Intent(this, SaveMessageActivity::class.java)
            startActivity(intent)
            finish()

        }


    }
}