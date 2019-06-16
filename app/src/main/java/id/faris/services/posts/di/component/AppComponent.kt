package id.faris.services.posts.di.component

import android.app.Application
import dagger.Component
import dagger.android.AndroidInjectionModule
import id.faris.services.posts.PostApplication
import id.faris.services.posts.di.modules.AppModule
import id.faris.services.posts.di.modules.BuildersModule
import id.faris.services.posts.di.modules.NetModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(AndroidInjectionModule::class, BuildersModule::class, AppModule::class,
        NetModule::class)
)
interface AppComponent {
    fun inject(app: PostApplication)
}