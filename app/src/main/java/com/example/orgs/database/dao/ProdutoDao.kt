package com.example.orgs.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.orgs.model.Produto

@Dao
interface ProdutoDao {

    @Query("SELECT * FROM Produto")
    fun getAll() : List<Produto>

    @Insert
    fun add(produto: Produto)

    @Delete
    fun delete(produto: Produto)

}