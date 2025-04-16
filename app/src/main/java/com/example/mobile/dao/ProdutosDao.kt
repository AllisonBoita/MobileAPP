package com.example.mobile.dao

import com.example.mobile.model.Produto
import java.math.BigDecimal

class ProdutosDao {

    fun adiciona(produto: Produto) {
        produtos.add(produto)
    }

    fun buscaTodos(): List<Produto> {
        return produtos.toList()
    }

    companion object {
        private val produtos = mutableListOf<Produto>(
            Produto(
                nome = "Cesta de Frutas",
                descricao = "Laranjas, maças e uvas",
                valor = BigDecimal("10.93"),
                disponivel = "Sim"

            ),
            Produto(nome = "Pack de bebida",
                descricao = "Large Beer",
                valor = BigDecimal("19.90"),
                disponivel = "Não"
            )
        )
    }

}