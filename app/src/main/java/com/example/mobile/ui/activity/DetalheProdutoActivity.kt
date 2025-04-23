package com.example.mobile.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mobile.databinding.ActivityDetalheProdutoBinding
import com.example.mobile.extensions.formataParaMoedaBrasileira
import com.example.mobile.extensions.tentaCarregarImagem
import com.example.mobile.model.Produto

class DetalheProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetalheProdutoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tentaCarregarProduto()
    }

    private fun tentaCarregarProduto() {
        // tentativa de buscar o produto se ele existir,
        // caso contrário, finalizar a Activity
        intent.getParcelableExtra<Produto>(CHAVE_PRODUTO)?.let { produtoCarregado ->
            preencheCampos(produtoCarregado)
        } ?: finish()
    }

    private fun preencheCampos(produtoCarregado: Produto) {
        with(binding) {
            activityDetalheProdutoImagem.tentaCarregarImagem(produtoCarregado.imagem)
            activityDetalheProdutoNome.text = produtoCarregado.nome
            activityDetalheProdutoDescricao.text = produtoCarregado.descricao
            activityDetalheProdutoValor.text =
                produtoCarregado.valor.formataParaMoedaBrasileira()
        }
    }

}