package id.faris.services.posts.di.modules

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import dagger.Module
import dagger.Provides
import id.faris.services.posts.data.source.local.Database
import id.faris.services.posts.data.source.local.PostsDao
import id.faris.services.posts.ui.list.PostsViewModelFactory
import javax.inject.Singleton

@Module
class AppModule(val app: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun providePostsDatabase(app: Application): Database = Room.databaseBuilder(app,
        Database::class.java, "posts_db").build()

    @Provides
    @Singleton
    fun providePostsDao(database: Database): PostsDao = database.postsDao()

    @Provides
    fun providePostsViewModelFactory(
        factory: PostsViewModelFactory): ViewModelProvider.Factory = factory
}
