package com.example.database2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.io.Serializable
import java.time.LocalDate
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var adapter: PessoaAdapter

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_principal, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val form = Intent(this, FormPessoa::class.java)
        startActivity(form)
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        this.load()
        super.onResume()
    }

    fun load() {
        val lista = findViewById<RecyclerView>(R.id.listaPessoas)

        GlobalScope.launch(Dispatchers.Main) {
            val pessoas = withContext(Dispatchers.IO) {
                AppDatabase.getInstance(this@MainActivity).pessoaDao().findAll()
            }

            adapter = PessoaAdapter(pessoas)
            lista.adapter = adapter

            lista.layoutManager = LinearLayoutManager(this@MainActivity,
                LinearLayout.VERTICAL, false)

        }

    }

    fun alterar() {
        val form = Intent(this, FormPessoa::class.java)
        val pessoa = this.adapter.getPessoa()
        form.putExtra("pessoa", pessoa)
        startActivity(form)
    }

    fun excluir() {
        val pessoa = this.adapter.getPessoa()

        val exceptionHandler = CoroutineExceptionHandler {
                coroutineContext, throwable -> toast(throwable.message!!)
        }

        GlobalScope.launch(Dispatchers.Main+exceptionHandler) {
            withContext(Dispatchers.IO) {
                AppDatabase.getInstance(this@MainActivity).pessoaDao().delete(pessoa)
            }
            this@MainActivity.load()
        }

    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when(item.itemId) {
                R.id.alterar -> alterar()
                R.id.excluir -> excluir()
            }
        }
        return super.onContextItemSelected(item)
    }

}
