package id.faris.services.posts.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.io.Serializable

@Entity(
    tableName = "posts"
)
data class Post (

    @Json(name = "id")
    @PrimaryKey
    val id: Int,

    @Json(name = "title")
    val title: String? = null
) : Serializable