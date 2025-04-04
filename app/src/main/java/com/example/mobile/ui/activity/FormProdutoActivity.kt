package com.example.mobile.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.mobile.R
import com.example.mobile.dao.ProdutosDao
import com.example.mobile.databinding.ActivityFormProdutoBinding
import com.example.mobile.databinding.ActivityListaProdutosBinding
import com.example.mobile.model.Produto
import java.math.BigDecimal

class FormProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormProdutoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoSalvar()
    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = binding.activityFormularioBotaoSalvar
        val dao = ProdutosDao()
        botaoSalvar.setOnClickListener {
            val produtoNovo = criaProduto()
            if (produtoNovo != null) {
                dao.adiciona(produtoNovo)
                finish()
            }
            val intent = Intent(this, SaveMessageActivity::class.java)
            startActivity(intent)
        }
    }

    private fun criaProduto(): Produto? {
        val campoNome = binding.activityFormularioProdutoNome
        val nome = campoNome.text.toString()
        Log.i("FormularioProduto", "onCreate: $nome")
        val campoDescricao = binding.activityFormularioProdutoDescricao
        val descricao = campoDescricao.text.toString()
        Log.i("FormularioProduto", "onCreate: $descricao")
        val campoValor = binding.activityFormularioProdutoValor
        val valorEmTexto = campoValor.text.toString()
        val valor = if (valorEmTexto.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorEmTexto)
        }
        val campoDisponivel = binding.activityFormularioProdutoDisponivel
        val disponivel = campoDisponivel.text.toString()

        if (nome.isBlank() || descricao.isBlank() || valorEmTexto.isBlank() || disponivel.isBlank()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show()
            return null
        } else {
            return Produto(nome, descricao, valor, disponivel)
        }

    }
}