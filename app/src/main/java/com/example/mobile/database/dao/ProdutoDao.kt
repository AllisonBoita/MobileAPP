package com.example.mobile.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mobile.model.Produto

@Dao
interface ProdutoDao {


    @Query("SELECT * FROM Produto")
    fun buscaTodos() : List<Produto>

    @Insert
    fun salva(produto: Produto)

    @Delete
    fun deletar(produto: Produto)

    @Update
    fun altera(produto: Produto)

}