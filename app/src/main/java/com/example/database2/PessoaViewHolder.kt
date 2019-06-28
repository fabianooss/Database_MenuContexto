package com.example.database2

import android.view.ContextMenu
import android.view.Menu
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PessoaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnCreateContextMenuListener{

    init {
        itemView.setOnCreateContextMenuListener(this)
    }

    fun bind(p: Pessoa) {
        itemView.findViewById<TextView>(R.id.pessoa_id).text = p.id.toString()
        itemView.findViewById<TextView>(R.id.pessoa_nome).text = p.nome.toString()
        itemView.findViewById<TextView>(R.id.pessoa_email).text = p.email.toString()
        itemView.findViewById<TextView>(R.id.pessoa_fone).text = p.fone.toString()
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        menu?.setHeaderTitle("Opções")
        menu?.add(this.adapterPosition, R.id.alterar, Menu.NONE, "Alterar")
        menu?.add(this.adapterPosition, R.id.excluir, Menu.NONE, "Excluir")

    }

}