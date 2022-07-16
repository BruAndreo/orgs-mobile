package com.example.orgs.dao

import com.example.orgs.model.Produto

class ProdutosDao {

    companion object {
        private val produtos = mutableListOf<Produto>()
    }

    fun add(produto: Produto) = produtos.add(produto)

    fun getAll(): List<Produto> = produtos.toList()

}