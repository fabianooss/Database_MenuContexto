package com.example.database2

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.time.LocalDate
import java.util.*


@Entity
data class Pessoa(

    var nome: String,
    var email: String,
    var fone: String) : Serializable {

    @PrimaryKey (autoGenerate = true)
    var id: Int = 0
}