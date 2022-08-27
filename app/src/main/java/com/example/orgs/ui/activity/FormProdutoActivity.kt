package com.example.orgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import coil.load
import com.example.orgs.R
import com.example.orgs.dao.ProdutosDao
import com.example.orgs.database.AppDatabase
import com.example.orgs.databinding.ActivityFormProdutoBinding
import com.example.orgs.databinding.FormularioImagemBinding
import com.example.orgs.extensions.loadImage
import com.example.orgs.model.Produto
import com.example.orgs.ui.dialog.FormImageDialog
import java.math.BigDecimal

class FormProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormProdutoBinding.inflate(layoutInflater)
    }

    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Cadastrar Produto"
        buildSaveBtn()

        binding.formProdutoImagem.setOnClickListener {
            FormImageDialog(this).show(this.url) {url ->
                this.url = url
                binding.formProdutoImagem.loadImage(url)
            }
        }
    }

    private fun buildSaveBtn() {
        val btnSalvar = binding.formProdutoBtnSalvar

        val produtosDao = AppDatabase.getInstance(this).produtoDao()

        btnSalvar.setOnClickListener {
            val produto = createProduto()
            produtosDao.add(produto)
            finish()
        }
    }

    private fun createProduto(): Produto {
        val nome = binding.formProdutoNome.text.toString()
        val descricao = binding.formProdutoDescricao.text.toString()
        val campoValor = binding.formProdutoValor.text.toString()
        val valor = if (campoValor.isBlank()) { BigDecimal.ZERO } else { BigDecimal(campoValor) }

        return Produto(0L, nome, descricao, valor, url)
    }

}