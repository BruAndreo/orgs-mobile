package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.orgs.R
import com.example.orgs.dao.ProdutosDao
import com.example.orgs.database.AppDatabase
import com.example.orgs.databinding.ActivityListaProdutosBinding
import com.example.orgs.ui.recyclerView.adapter.ListaProdutosAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListaProdutosActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityListaProdutosBinding.inflate(layoutInflater)
    }
    private val produtos = ProdutosDao()
    private val adapter = ListaProdutosAdapter(context = this, produtos = produtos.getAll()) {
        Log.i("ListaProdutosActivity", "click in ${it.nome}")
        val intent = Intent(this, DetalhesProdutoActivity::class.java).apply {
            putExtra("produtoNome", it)
        }
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        buildRecyclerView()
        buildFab()
    }

    override fun onResume() {
        super.onResume()

        val db = AppDatabase.getInstance(this)
        val produtosDao = db.produtoDao()
        adapter.refresh(produtosDao.getAll())
    }

    private fun buildRecyclerView() {
        val listaProdutosView = binding.listaProdutos
        listaProdutosView.adapter = adapter
    }

    private fun buildFab() {
        val fab = binding.addProduto
        fab.setOnClickListener {
            openFormProdutos()
        }
    }

    private fun openFormProdutos() {
        val intent = Intent(this, FormProdutoActivity::class.java)
        startActivity(intent)
    }

}