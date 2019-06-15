package id.faris.services.posts.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.faris.services.posts.model.Post

@Dao
interface PostsDao {

    @Query("SELECT * FROM posts")
    fun queryPosts(): LiveData<List<Post>>

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    fun insertPost(post: Post)
}
