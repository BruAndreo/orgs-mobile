package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.R
import com.example.orgs.dao.ProdutosDao
import com.example.orgs.ui.recyclerView.adapter.ListaProdutosAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListaProdutosActivity : AppCompatActivity(R.layout.activity_lista_produtos) {

    private val produtos = ProdutosDao()
    private val adapter = ListaProdutosAdapter(context = this, produtos = produtos.getAll())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buildRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        adapter.refresh(produtos.getAll())
        buildFab()
    }

    private fun buildRecyclerView() {
        val listaProdutosView = findViewById<RecyclerView>(R.id.listaProdutos)
        listaProdutosView.adapter = adapter
    }

    private fun buildFab() {
        val fab = findViewById<FloatingActionButton>(R.id.add_produto)
        fab.setOnClickListener {
            openFormProdutos()
        }
    }

    private fun openFormProdutos() {
        val intent = Intent(this, FormProdutoActivity::class.java)
        startActivity(intent)
    }

}