package com.example.orgs.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.example.orgs.databinding.FormularioImagemBinding
import com.example.orgs.extensions.loadImage

class FormImageDialog(private val context: Context) {

    fun show(urlExistent: String? = null, setUrl: (url: String?) -> Unit) {
        val binding = FormularioImagemBinding.inflate(LayoutInflater.from(context))
        var url: String? = null

        urlExistent?.let {
            binding.formularioImagemImageview.loadImage(it)
            binding.formularioImagemTextinputUrl.setText(it)
        }

        binding.formularioImagemCarregar.setOnClickListener {
            url = binding.formularioImagemTextinputUrl.text.toString()
            binding.formularioImagemImageview.loadImage(url)
        }

        AlertDialog.Builder(context)
            .setView(binding.root)
            .setPositiveButton("Confirmar") {_,_ ->
//                binding.formProdutoImagem.loadImage(url)
                setUrl(url)
            }
            .setNegativeButton("Cancelar") {_,_ ->}
            .show()
    }

}