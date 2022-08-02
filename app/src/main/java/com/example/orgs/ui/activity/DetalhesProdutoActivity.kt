package com.example.orgs.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import coil.load
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

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        produto = intent.extras?.getParcelable<Produto>("produtoNome")!!

        binding.detalhesProdutoImagem.loadImage(produto.imagem)
        binding.detalhesProdutoNome.text = produto.nome
        binding.detalhesProdutoDescricao.text = produto.descricao
        binding.detalhesProdutoValor.text = formataValor(produto.valor)
    }

    private fun formataValor(valor: BigDecimal): String {
        val formatter = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
        return formatter.format(valor)
    }
}