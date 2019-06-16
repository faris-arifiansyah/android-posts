package id.faris.services.posts.data.source.remote

import id.faris.services.posts.data.Post
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiInterface {

    @GET("posts/")
    fun getPosts(): Observable<List<Post>>
}