package com.example.orgs.ui.recyclerView.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.orgs.R
import com.example.orgs.databinding.ProdutoItemBinding
import com.example.orgs.extensions.loadImage
import com.example.orgs.model.Produto
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

class ListaProdutosAdapter(
    private val context: Context,
    produtos: List<Produto> = emptyList(),
    var clickItemListener: (produto: Produto) -> Unit = {}
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    private val produtos = produtos.toMutableList()

    inner class ViewHolder(binding: ProdutoItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var produto: Produto

        init {
            itemView.setOnClickListener {
//                Log.i("ListaProdutosAdapter", "Click item")
                if (::produto.isInitialized) {
                    clickItemListener(produto)
                }
            }
        }

        private val nome = binding.produtoItemNome
        private val descricao = binding.produtoItemDescricao
        private val valor = binding.produtoItemValor
        private val imageView = binding.produtoItemImagem

        fun vincula(produto: Produto) {
            this.produto = produto
            nome.text = produto.nome
            descricao.text = produto.descricao
            valor.text = formataValor(produto.valor)
            imageView.loadImage(produto.imagem)
        }

        private fun formataValor(valor: BigDecimal): String {
            val formatter = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
            return formatter.format(valor)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProdutoItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
        holder.vincula(produto)
    }

    override fun getItemCount(): Int = produtos.size

    @SuppressLint("NotifyDataSetChanged")
    fun refresh(produtos: List<Produto>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()
    }

}
