package com.example.shoppingcart.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shoppingcart.room.table.DatabaseProductsTable

@Dao
interface CartDao {
    @Query("SELECT * FROM product_table")
    fun getAllProduct(): LiveData<List<DatabaseProductsTable>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllProduct(list: List<DatabaseProductsTable>)
}

@Database(entities = [DatabaseProductsTable::class], version = 1, exportSchema = false)
abstract class CartDatabase : RoomDatabase() {
    abstract val cartDao: CartDao
}