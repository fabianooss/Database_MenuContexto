package com.example.database2

import androidx.room.*

@Dao
interface PessoaDao {

    @Insert
    fun insert(pessoa: Pessoa)

    @Update
    fun update(pessoa: Pessoa)

    @Delete
    fun delete(pessoa: Pessoa)

    @Query("select * from Pessoa order by nome")
    fun findAll(): List<Pessoa>

    @Query("select * from Pessoa where id=:id")
    fun findById(id: Int): Pessoa
}
