package com.example.mobile.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.mobile.R
import com.example.mobile.database.AppDatabase
import com.example.mobile.databinding.ActivityDetalheProdutoBinding
import com.example.mobile.extensions.formataParaMoedaBrasileira
import com.example.mobile.extensions.tentaCarregarImagem
import com.example.mobile.model.Produto

private const val TAG = "DetalhesProduto"

class DetalheProdutoActivity : AppCompatActivity() {

    private lateinit var produto: Produto
    private val binding by lazy {
        ActivityDetalheProdutoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tentaCarregarProduto()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalhes_produto, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (::produto.isInitialized) {
            val db = AppDatabase.instancia(this)
            val produtoDao = db.produtoDao()
            when(item.itemId){
                R.id.menu_detalhes_produto_remover -> {
                    Log.i(TAG, "onOptionsItemSelected: remover ")
                    produtoDao.deletar(produto)
                    finish()
                    return true
                }
                R.id.menu_detalhes_produto -> {
                    Log.i(TAG, "onOptionsItemSelected: editar")
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun tentaCarregarProduto() {
        // tentativa de buscar o produto se ele existir,
        // caso contrário, finalizar a Activity
        intent.getParcelableExtra<Produto>(CHAVE_PRODUTO)?.let { produtoCarregado ->
            produto = produtoCarregado
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