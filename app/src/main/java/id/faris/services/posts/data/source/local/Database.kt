package id.faris.services.posts.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import id.faris.services.posts.data.Post

@Database(entities = arrayOf(Post::class), version = 1)
abstract class Database : RoomDatabase() {
    abstract fun postsDao() : PostsDao
}