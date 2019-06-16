package id.faris.services.posts.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.faris.services.posts.R
import id.faris.services.posts.data.Post
import java.util.ArrayList


class PostsAdapter(
    posts: List<Post>?) : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val postItem = postsList[position]
        holder?.postListItem(postItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(
            R.layout.post_list_item,
            parent, false)
        return PostsViewHolder(itemView)
    }

    private var postsList = ArrayList<Post>()

    init {
        this.postsList = posts as ArrayList<Post>
    }

    override fun getItemCount(): Int {
        return postsList.size
    }

    fun addPosts(posts: List<Post>){
        val initPosition = postsList.size
        postsList.addAll(posts)
        notifyItemRangeInserted(initPosition, postsList.size)
    }

    class PostsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var postId = itemView.findViewById<TextView>(R.id.post_id)

        fun postListItem(postItem: Post) {
            postId.text = postItem.title
        }
    }
}