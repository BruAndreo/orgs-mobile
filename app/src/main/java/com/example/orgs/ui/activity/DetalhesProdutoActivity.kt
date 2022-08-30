package com.example.orgs.ui.activity

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

    private lateinit var produto: Produto
    private val binding by lazy {
        ActivityDetalhesProdutoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        produto = intent.extras?.getParcelable<Produto>("produtoNome")!!

        binding.detalhesProdutoImagem.loadImage(produto.imagem)
        binding.detalhesProdutoNome.text = produto.nome
        binding.detalhesProdutoDescricao.text = produto.descricao
        binding.detalhesProdutoValor.text = formataValor(produto.valor)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalhes_produto, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (::produto.isInitialized) {
            val db = AppDatabase.getInstance(this).produtoDao()

            when(item.itemId) {
                R.id.menu_detalhes_produto_editar -> {
                    Log.i("Meu menu", "Editar")
                    db.delete(produto)
                    finish()
                }
                R.id.menu_detalhes_produto_deletar -> {
                    Log.i("Meu menu", "Deletar")
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun formataValor(valor: BigDecimal): String {
        val formatter = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
        return formatter.format(valor)
    }
}