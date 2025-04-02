package com.example.mobile.dao

import com.example.mobile.model.Produto

class ProdutosDao {

    private val produtos = mutableListOf<Produto>()

    fun adiciona(produto: Produto){
        produtos.add(produto)
    }

    fun buscaTodos() : List<Produto> {
        return produtos.toList()
    }

}