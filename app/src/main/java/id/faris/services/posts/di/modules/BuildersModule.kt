package id.faris.services.posts.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.faris.services.posts.ui.list.MainActivity

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun contributePostsActivity() : MainActivity
}