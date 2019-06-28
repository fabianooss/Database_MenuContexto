package com.example.database2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.coroutines.*
import org.jetbrains.anko.toast

class FormPessoa : AppCompatActivity() {

    lateinit var pessoa : Pessoa

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_pessoa)

        val parameter = intent.extras?.get("pessoa")
        if (parameter != null) {
            pessoa = parameter as Pessoa
            findViewById<EditText>(R.id.edNome).setText(pessoa.nome)
            findViewById<EditText>(R.id.edEmail).setText(pessoa.email)
            findViewById<EditText>(R.id.edFone).setText(pessoa.fone)
        }
        else {
            pessoa = Pessoa("","","")
        }
        findViewById<Button>(R.id.btnSalvar).setOnClickListener {

            val exceptionHandler = CoroutineExceptionHandler {
                    coroutineContext, throwable -> toast(throwable.message!!)
            }

            GlobalScope.launch(Dispatchers.Main+exceptionHandler) {
                withContext(Dispatchers.IO) {
                    pessoa.nome = findViewById<EditText>(R.id.edNome).text.toString()
                    pessoa.email = findViewById<EditText>(R.id.edEmail).text.toString()
                    pessoa.fone = findViewById<EditText>(R.id.edFone).text.toString()

                    if (pessoa.id == 0)
                        AppDatabase.getInstance(this@FormPessoa).pessoaDao().insert(pessoa)
                    else
                        AppDatabase.getInstance(this@FormPessoa).pessoaDao().update(pessoa)
                }
                this@FormPessoa.finish()
            }

        }
    }


}
