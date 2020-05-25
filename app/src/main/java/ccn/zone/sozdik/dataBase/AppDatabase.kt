package ccn.zone.sozdik.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ccn.zone.sozdik.dao.MyDao

import ccn.zone.sozdik.entity.Words

@Database(entities = [Words::class], version = 2)

abstract class AppDatabase: RoomDatabase() {
    abstract fun myDao(): MyDao
    companion object {
        var INSTANCE: AppDatabase? = null
        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "myDB"
                    ).build()
                }
            }
            return INSTANCE
        }
        fun destroyDataBase() {
            INSTANCE = null
        }

    }
}