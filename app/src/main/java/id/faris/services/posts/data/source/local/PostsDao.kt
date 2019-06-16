package id.faris.services.posts.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.faris.services.posts.data.Post
import io.reactivex.Single

@Dao
interface PostsDao {

    @Query("SELECT * FROM posts ORDER BY id limit :limit offset :offset")
    fun queryPosts(limit:Int, offset:Int): Single<List<Post>>

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    fun insertPost(post: Post)

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    fun insertAllPosts(posts: List<Post>)
}
