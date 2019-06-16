package id.faris.services.posts.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject


class PostsViewModelFactory @Inject constructor(
    private val postsViewModel: PostsViewModel) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostsViewModel::class.java!!)) {
            return postsViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
