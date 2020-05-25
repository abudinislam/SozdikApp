package ccn.zone.sozdik.dao

import androidx.room.*
import ccn.zone.sozdik.entity.Words

@Dao
interface MyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTable(storageOfWords: Words)

    @Update
    fun updateTable(storageOfWords: Words)

    @Delete
    fun deleteTable(storageOfWords: Words)

   /* @Query ("SELECT * FROM myDB")
    fun getTable(): MutableList<Words>

    */



}