package id.faris.services.posts.api

import id.faris.services.posts.model.Post
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiInterface {

    @GET("posts/")
    fun getPosts(): Observable<List<Post>>
}