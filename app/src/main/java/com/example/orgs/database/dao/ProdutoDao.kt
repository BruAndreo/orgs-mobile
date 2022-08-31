package com.example.orgs.database.dao

import androidx.room.*
import com.example.orgs.model.Produto

@Dao
interface ProdutoDao {

    @Query("SELECT * FROM Produto")
    fun getAll() : List<Produto>

    @Insert
    fun add(produto: Produto)

    @Delete
    fun delete(produto: Produto)

    @Update
    fun update(produto: Produto)

    @Query("SELECT * FROM Produto WHERE id = :id")
    fun getById(id: Long) : Produto?

}