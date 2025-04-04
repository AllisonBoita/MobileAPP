package com.example.mobile.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile.R
import com.example.mobile.databinding.ProductItemBinding
import com.example.mobile.model.Produto

class ListaProdutosAdapter (
    private val context: Context,
    produtos: List<Produto>
): RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    private val produtos = produtos.toMutableList()

    class ViewHolder(private val binding: ProductItemBinding):
        RecyclerView.ViewHolder(binding.root){
        fun vincula(produto: Produto) {
            val nome = binding.productItemNome
            nome.text = produto.nome
            val descricao = binding.productItemDescricao
            descricao.text = produto.descricao
            val valor = binding.productItemValor
            valor.text = produto.valor.toPlainString()
            val disponivel = binding.productItemDisponivel
            disponivel.text = produto.disponivel
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
