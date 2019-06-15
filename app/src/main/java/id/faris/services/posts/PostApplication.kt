package id.faris.services.posts

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import id.faris.services.posts.di.component.DaggerAppComponent
import id.faris.services.posts.di.modules.AppModule
import javax.inject.Inject

class PostApplication: Application(), HasActivityInjector {

    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder().appModule(AppModule(this)).build().inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}