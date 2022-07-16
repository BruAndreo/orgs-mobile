package com.example.orgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.orgs.R
import com.example.orgs.dao.ProdutosDao
import com.example.orgs.model.Produto
import java.math.BigDecimal

class FormProdutoActivity : AppCompatActivity(R.layout.activity_form_produto) {

    private val produtosDao = ProdutosDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buildSaveBtn()
    }

    private fun buildSaveBtn() {
        val btnSalvar = findViewById<Button>(R.id.form_produto_btn_salvar)
        btnSalvar.setOnClickListener {
            val produto = createProduto()
            produtosDao.add(produto)
            finish()
        }
    }

    private fun createProduto(): Produto {
        val nome = findViewById<EditText>(R.id.form_produto_nome).text.toString()
        val descricao = findViewById<EditText>(R.id.form_produto_descricao).text.toString()
        val campoValor = findViewById<EditText>(R.id.form_produto_valor).text.toString()
        val valor = if (campoValor.isBlank()) { BigDecimal.ZERO } else { BigDecimal(campoValor) }

        return Produto(nome, descricao, valor)
    }

}