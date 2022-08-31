package com.example.orgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.orgs.database.AppDatabase
import com.example.orgs.databinding.ActivityFormProdutoBinding
import com.example.orgs.extensions.loadImage
import com.example.orgs.model.Produto
import com.example.orgs.ui.dialog.FormImageDialog
import java.math.BigDecimal

class FormProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormProdutoBinding.inflate(layoutInflater)
    }

    private val db by lazy {
        AppDatabase.getInstance(this).produtoDao()
    }

    private var url: String? = null
    private var idProduto = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        buildSaveBtn()

        idProduto = intent.getLongExtra("idProduto", 0L)

        title = "Cadastrar Produto"

        binding.formProdutoImagem.setOnClickListener {
            FormImageDialog(this).show(this.url) {url ->
                this.url = url
                binding.formProdutoImagem.loadImage(url)
            }
        }
    }

    override fun onResume() {
        super.onResume()

        db.getById(idProduto)?.let {
            title = "Alterar Produto"
            loadInfos(it)
        } ?: finish()
    }

    private fun loadInfos(produto: Produto) {
        url = produto.imagem
        binding.formProdutoImagem.loadImage(produto.imagem)
        binding.formProdutoNome.setText(produto.nome)
        binding.formProdutoDescricao.setText(produto.descricao)
        binding.formProdutoValor.setText(produto.valor.toPlainString())
    }

    private fun buildSaveBtn() {
        val btnSalvar = binding.formProdutoBtnSalvar

        btnSalvar.setOnClickListener {
            val produto = createProduto()

            if (idProduto > 0) {
                db.update(produto)
            }
            else {
                db.add(produto)
            }

            finish()
        }
    }

    private fun createProduto(): Produto {
        val nome = binding.formProdutoNome.text.toString()
        val descricao = binding.formProdutoDescricao.text.toString()
        val campoValor = binding.formProdutoValor.text.toString()
        val valor = if (campoValor.isBlank()) { BigDecimal.ZERO } else { BigDecimal(campoValor) }

        return Produto(idProduto, nome, descricao, valor, url)
    }

}