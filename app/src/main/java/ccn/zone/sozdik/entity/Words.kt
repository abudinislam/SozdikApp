package ccn.zone.sozdik.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "myDB")
data class Words (
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val Id: Int? = null,
    var textKir : String,
    var textLat : String
) : info {
    override fun toGether(): String {
        return "$textKir - $textLat"
    }
}

interface info {
    fun toGether() : String
}
