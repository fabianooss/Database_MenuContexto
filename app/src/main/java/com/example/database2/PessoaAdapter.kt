package com.example.database2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.View.OnLongClickListener

class PessoaAdapter(val pessoas: List<Pessoa>) : RecyclerView.Adapter<PessoaViewHolder>() {

    var position = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PessoaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_pessoa, parent,
            false)
        return PessoaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pessoas.size
    }

    override fun onBindViewHolder(holder: PessoaViewHolder, position: Int) {
        holder.bind(pessoas.get(position))
        holder.itemView.setOnLongClickListener {
            this.position = position
            false
        }
    }

    fun getPessoa() : Pessoa {
        return this.pessoas.get(position)
    }

}