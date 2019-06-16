package id.faris.services.posts.data.source

import android.util.Log
import id.faris.services.posts.data.Post
import id.faris.services.posts.data.source.local.PostsDao
import id.faris.services.posts.data.source.remote.ApiInterface
import io.reactivex.Observable
import javax.inject.Inject


class PostRepository @Inject constructor(val apiInterface: ApiInterface,
                                         val postsDao: PostsDao) {

    fun getPosts(): Observable<List<Post>> {
        val observableFromApi = getPostsFromApi()
        val observableFromDb = getPostsFromDb()
        return Observable.concatArrayEager(observableFromApi, observableFromDb)
    }

    fun getPostsFromApi(): Observable<List<Post>> {
        return apiInterface.getPosts()
            .doOnNext {
                Log.e("REPOSITORY API * ", it.size.toString())
                for (item in it) {
                    postsDao.insertPost(item)
                }
            }
    }

    fun getPostsFromDb(): Observable<List<Post>> {
        return postsDao.queryPosts()
            .toObservable()
            .doOnNext {
                //Print log it.size :)
                Log.e("REPOSITORY DB *** ", it.size.toString())
            }
    }
}