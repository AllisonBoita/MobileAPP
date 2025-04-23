package com.example.mobile.ui.recyclerview.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class ListaProdutosAdapter (
    private val context: Context,
    produtos: List<Produto>,
    var quandoClicaNoItem: (produto: Produto) -> Unit = {}

): RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    private val produtos = produtos.toMutableList()

    inner class ViewHolder(private val binding: ProductItemBinding):
        RecyclerView.ViewHolder(binding.root){

        private lateinit var produto: Produto

        init {
            // implementação do listener do adapter
            itemView.setOnClickListener {
                // verificação da existência de valores em property lateinit
                if (::produto.isInitialized) {
                    quandoClicaNoItem(produto)
                }
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

            //TODO("DE MOMENTO SE EU CLICO ELE ABRE, MAS ADICIONA OUTRO SE SALVO")

            /*if (!produto.imagem.isNullOrBlank()) {
                binding.imageView.visibility = View.VISIBLE
                binding.imageView.load(produto.imagem) {
                    fallback(R.drawable.erro) // se a imagem for null
                    error(R.drawable.erro) // se falhar o carregamento
                    placeholder(R.drawable.placeholder) // opcional
                }
            } else {
                binding.imageView.visibility = View.GONE
            }*/

            binding.imageView.visibility = View.VISIBLE
            binding.imageView.tentaCarregarImagem(produto.imagem)

            //TODO("ADICIONAR FORMA DE CLICAR NO CARD E CONSEGUIR CARREGAR IMAGEM COM ELE JÁ CRIADO")

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
