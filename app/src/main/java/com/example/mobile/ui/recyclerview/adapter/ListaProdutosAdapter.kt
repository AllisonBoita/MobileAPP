package com.example.mobile.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mobile.R
import com.example.mobile.databinding.ProductItemBinding
import com.example.mobile.model.Produto
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

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
            val valorEmMoeda: String = formataValorEmMoedaBrasileira(produto.valor)
            valor.text = valorEmMoeda
            val disponivel = binding.productItemDisponivel
            disponivel.text = produto.disponivel

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
            binding.imageView.load(produto.imagem) {
                fallback(R.drawable.erro) // aparece se for null ou blank
                error(R.drawable.erro)    // aparece se der erro no carregamento
            }
        }

        private fun formataValorEmMoedaBrasileira(valor: BigDecimal): String {
            val formatadorValor: NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
            return formatadorValor.format(valor)
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
