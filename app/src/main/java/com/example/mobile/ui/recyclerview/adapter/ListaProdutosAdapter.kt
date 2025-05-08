package com.example.mobile.ui.recyclerview.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mobile.R
import com.example.mobile.databinding.ProductItemBinding
import com.example.mobile.extensions.formataParaMoedaBrasileira
import com.example.mobile.extensions.tentaCarregarImagem
import com.example.mobile.model.Produto
import com.example.mobile.ui.activity.DetalheProdutoActivity
import com.example.mobile.ui.activity.FormProdutoActivity
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

private const val TAG = "ListaProduto"

class ListaProdutosAdapter (
    private val context: Context,
    produtos: List<Produto> = emptyList(),
    var quandoClicaNoItem: (produto: Produto) -> Unit = {},
    var quandoClicaEmEditar: (produto: Produto) -> Unit = {},
    var quandoClicaEmRemover: (produto: Produto) -> Unit = {}


): RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    private val produtos = produtos.toMutableList()

    inner class ViewHolder(private val binding: ProductItemBinding):
        RecyclerView.ViewHolder(binding.root), PopupMenu.OnMenuItemClickListener {

        private lateinit var produto: Produto

        init {
            // implementação do listener do adapter
            itemView.setOnClickListener {
                // verificação da existência de valores em property lateinit
                if (::produto.isInitialized) {
                    quandoClicaNoItem(produto)
                }
            }

            itemView.setOnLongClickListener {
                PopupMenu(context, itemView).apply {
                    menuInflater.inflate(
                        R.menu.menu_detalhes_produto,
                        menu
                    )
                    setOnMenuItemClickListener(this@ViewHolder)
                }.show()
                true
            }
        }

        fun vincula(produto: Produto) {
            this.produto = produto
            val nome = binding.productItemNome
            nome.text = produto.nome
            val descricao = binding.productItemDescricao
            descricao.text = produto.descricao
            val valor = binding.productItemValor
            val valorEmMoeda: String = produto.valor.formataParaMoedaBrasileira()
            valor.text = valorEmMoeda
            val disponivel = binding.productItemDisponivel
            disponivel.text = produto.disponivel

            binding.imageView.visibility = View.VISIBLE
            binding.imageView.tentaCarregarImagem(produto.imagem)

            //TODO("ADICIONAR FORMA DE CLICAR NO CARD E CONSEGUIR CARREGAR IMAGEM COM ELE JÁ CRIADO")

        }

        override fun onMenuItemClick(item: MenuItem?): Boolean {
            item?.let {
                when (item.itemId) {
                    R.id.menu_detalhes_produto -> {
                        quandoClicaEmEditar(produto)
                        Log.i(TAG, "onMenuItemClick: editar")
                    }
                    R.id.menu_detalhes_produto_remover -> {
                        quandoClicaEmRemover
                        Log.i(TAG, "onMenuItemClick: remover")
                    }

                    else -> {}
                }
            }
            return true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ProductItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)

    }

    override fun getItemCount(): Int = produtos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
        holder.vincula(produto)
        
    }

    fun atualiza(produtos: List<Produto>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()
    }

}
