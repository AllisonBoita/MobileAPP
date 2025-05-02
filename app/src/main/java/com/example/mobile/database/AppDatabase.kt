package com.example.mobile.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mobile.database.converter.Converters
import com.example.mobile.database.dao.ProdutoDao
import com.example.mobile.model.Produto

@Database(entities = [Produto::class], version = 1)

@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun produtoDao() : ProdutoDao
}