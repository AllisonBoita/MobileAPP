package com.example.mobile.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mobile.model.Produto

@Dao
class ProdutoDao {

    @Query("SELECT * FROM Produto")
    fun buscaTodos(): List<Produto>

    @Insert
    fun salva(vararg produto: Produto)
}