package id.faris.services.posts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import id.faris.services.posts.api.ApiClient
import id.faris.services.posts.api.ApiInterface
import id.faris.services.posts.model.Post
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showPosts()
    }

    private fun showPosts() {
        val postsResponse = getPosts()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())

        val disposableObserver =
            postsResponse.subscribeWith(object : DisposableObserver<List<Post>>() {
                override fun onComplete() {
                }

                override fun onNext(posts: List<Post>) {
                    val listSize = posts.size
                    Log.e("ITEMS **** ", listSize.toString())

                    for (post in posts) {
                        Log.e("ID : ", post.id.toString())
                        Log.e("Title : ", post.title)
                    }
                }

                override fun onError(e: Throwable) {
                    Log.e("ERROR *** ", e.message)
                }

            })

        compositeDisposable.addAll(disposableObserver)
    }

    private fun getPosts(): Observable<List<Post>> {
        val retrofit = ApiClient.getClient()
        val apiInterface = retrofit.create(ApiInterface::class.java)
        return apiInterface.getPosts()
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}
