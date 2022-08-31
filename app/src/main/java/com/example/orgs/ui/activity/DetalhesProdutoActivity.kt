package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.orgs.R
import com.example.orgs.database.AppDatabase
import com.example.orgs.databinding.ActivityDetalhesProdutoBinding
import com.example.orgs.extensions.loadImage
import com.example.orgs.model.Produto
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

class DetalhesProdutoActivity: AppCompatActivity() {

    private var idProduto: Long = 0L
    private var produto: Produto? = null
    private val binding by lazy {
        ActivityDetalhesProdutoBinding.inflate(layoutInflater)
    }

    private val db by lazy {
        AppDatabase.getInstance(this).produtoDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        idProduto = intent.getLongExtra("idProduto", 0L)
    }

    override fun onResume() {
        super.onResume()
        db.getById(idProduto)?.let {
            produto = it
            loadInfos(it)
        } ?: finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalhes_produto, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (produto != null) {
            when(item.itemId) {
                R.id.menu_detalhes_produto_editar -> {
                    Log.i("Meu menu", "Editar")
                    Intent(this, FormProdutoActivity::class.java).apply {
                        putExtra("idProduto", idProduto)
                        startActivity(this)
                    }
                }
                R.id.menu_detalhes_produto_deletar -> {
                    Log.i("Meu menu", "Deletar")
                    db.delete(produto!!)
                    finish()
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun loadInfos(produto: Produto) {
        binding.detalhesProdutoImagem.loadImage(produto.imagem)
        binding.detalhesProdutoNome.setText(produto.nome)
        binding.detalhesProdutoDescricao.setText(produto.descricao)
        binding.detalhesProdutoValor.setText(produto.valor.toPlainString())
    }

    private fun formataValor(valor: BigDecimal): String {
        val formatter = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
        return formatter.format(valor)
    }
}