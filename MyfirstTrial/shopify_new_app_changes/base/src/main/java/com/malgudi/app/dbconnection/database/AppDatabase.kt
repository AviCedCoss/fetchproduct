package com.malgudi.app.dbconnection.database
import androidx.room.Database
import androidx.room.RoomDatabase
import com.malgudi.app.dbconnection.dao.AppLocalDataDao
import com.malgudi.app.dbconnection.dao.CartItemDataDao
import com.malgudi.app.dbconnection.dao.ItemDataDao
import com.malgudi.app.dbconnection.dao.LivePreviewDao
import com.malgudi.app.dbconnection.entities.*

@Database(entities = [AppLocalData::class, UserLocalData::class, CustomerTokenData::class, ItemData::class, CartItemData::class, LivePreviewData::class], version = 10)
abstract class AppDatabase : RoomDatabase() {
    abstract val itemDataDao: ItemDataDao
    abstract val cartItemDataDao: CartItemDataDao
    abstract fun appLocalDataDaoDao(): AppLocalDataDao
    abstract fun getLivePreviewDao(): LivePreviewDao
}
