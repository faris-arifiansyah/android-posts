package id.faris.services.posts.ui.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import id.faris.services.posts.R.layout
import id.faris.services.posts.data.Post
import kotlinx.android.synthetic.main.activity_main.hello_world_textview
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var postsViewModelFactory: PostsViewModelFactory
    lateinit var postsViewModel: PostsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        AndroidInjection.inject(this)

        postsViewModel = ViewModelProviders.of(this, postsViewModelFactory).get(
            PostsViewModel::class.java)

        postsViewModel.loadPosts()

        postsViewModel.postsResult().observe(this,
            Observer<List<Post>> {
                hello_world_textview.text = "Hello ${it?.size} posts"
            })

        postsViewModel.postsError().observe(this, Observer<String>{
            hello_world_textview.text = "Hello error $it"
        })
    }

    override fun onDestroy() {
        postsViewModel.disposeElements()
        super.onDestroy()
    }
}
