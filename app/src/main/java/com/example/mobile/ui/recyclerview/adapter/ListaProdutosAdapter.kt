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
import com.example.mobile.extensions.tentaCarregarImagem
import com.example.mobile.model.Produto
import com.example.mobile.ui.activity.FormProdutoActivity
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

            // Configura o clique no item do card
            binding.containerCard.setOnClickListener {
                val intent = Intent(binding.root.context, FormProdutoActivity::class.java).apply {
                    putExtra("PRODUTO_ID", produto.nome)
                }
                binding.root.context.startActivity(intent)
            }

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
