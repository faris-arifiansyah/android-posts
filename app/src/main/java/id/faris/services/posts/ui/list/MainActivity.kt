package id.faris.services.posts.ui.list

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.AndroidInjection
import id.faris.services.posts.R
import id.faris.services.posts.R.layout
import id.faris.services.posts.data.Post
import id.faris.services.posts.utils.Constants
import id.faris.services.posts.utils.InfiniteScrollListener
import kotlinx.android.synthetic.main.activity_posts.progressBar
import kotlinx.android.synthetic.main.activity_posts.recycler
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var postsViewModelFactory: PostsViewModelFactory
    var postsAdapter = PostsAdapter(ArrayList())
    lateinit var postsViewModel: PostsViewModel
    var currentPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_posts)
        AndroidInjection.inject(this)

        initializeRecycler()

        postsViewModel = ViewModelProviders.of(this, postsViewModelFactory).get(
            PostsViewModel::class.java
        )

        progressBar.visibility = View.VISIBLE
        loadData()

        postsViewModel.postsResult().observe(this,
            Observer<List<Post>> {
                if (it != null) {
                    val position = postsAdapter.itemCount
                    postsAdapter.addPosts(it)
                    recycler.adapter = postsAdapter
                    recycler.scrollToPosition(position - Constants.LIST_SCROLLING)
                }
            })

        postsViewModel.postsError().observe(this, Observer<String> {
            if (it != null) {
                Toast.makeText(
                    this, resources.getString(R.string.post_error_message) + it,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        postsViewModel.postsLoader().observe(this, Observer<Boolean> {
            if (it == false) progressBar.visibility = View.GONE
        })
    }

    private fun initializeRecycler() {
        val gridLayoutManager = GridLayoutManager(this, 1)
        gridLayoutManager.orientation = RecyclerView.VERTICAL
        recycler.apply {
            setHasFixedSize(true)
            layoutManager = gridLayoutManager
            addOnScrollListener(InfiniteScrollListener({ loadData() }, gridLayoutManager))
        }
    }

    fun loadData() {
        postsViewModel.loadPosts(Constants.LIMIT, currentPage * Constants.OFFSET)
        currentPage++
    }

    override fun onDestroy() {
        postsViewModel.disposeElements()
        super.onDestroy()
    }
}
