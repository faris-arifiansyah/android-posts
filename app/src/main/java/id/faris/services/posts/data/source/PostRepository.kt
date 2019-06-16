package id.faris.services.posts.data.source

import android.util.Log
import id.faris.services.posts.data.Post
import id.faris.services.posts.data.source.local.PostsDao
import id.faris.services.posts.data.source.remote.ApiInterface
import id.faris.services.posts.utils.Utils
import io.reactivex.Observable
import javax.inject.Inject


class PostRepository @Inject constructor(val apiInterface: ApiInterface,
                                         val postsDao: PostsDao, val utils: Utils
) {

    fun getPosts(limit:Int, offset:Int): Observable<List<Post>> {
        val hasConnection = utils.isConnectedToInternet()
        var observableFromApi: Observable<List<Post>>? = null
        if (hasConnection){
            observableFromApi = getPostsFromApi()
        }
        val observableFromDb = getPostsFromDb(limit, offset)

        return if (hasConnection) Observable.concatArrayEager(observableFromApi, observableFromDb)
        else observableFromDb
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

    fun getPostsFromDb(limit:Int, offset:Int): Observable<List<Post>> {
        return postsDao.queryPosts(limit, offset)
            .toObservable()
            .doOnNext {
                //Print log it.size :)
                Log.e("REPOSITORY DB *** ", it.size.toString())
            }
    }
}