package ru.dekabrsky.consecutivepractice2025.listWithDetails.presentation.viewModel

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.back
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent
import ru.dekabrsky.consecutivepractice2025.core.coroutinesUtils.launchLoadingAndError
import ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.entity.MovieFullEntity
import ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.entity.MovieShortEntity
import ru.dekabrsky.consecutivepractice2025.listWithDetails.domain.repository.IMoviesRepository
import ru.dekabrsky.consecutivepractice2025.listWithDetails.presentation.state.MovieDetailState


class DetailsViewModel(
    private val repository: IMoviesRepository,
    private val navigation: StackNavContainer,
    private val id: String
): ViewModel() {

    private val context: Context by KoinJavaComponent.inject(Context::class.java)

    private val mutableState = MutableDetailsState()
    val viewState = mutableState as MovieDetailState

    init {
        viewModelScope.launchLoadingAndError(
            handleError = { mutableState.error = it.localizedMessage },
            updateLoading = { mutableState.isLoading = it }
        ) {
            mutableState.movie = repository.getById(id)
            mutableState.movie?.title?.let {
                launch { mutableState.related = repository.getList(it).take(3) }
            }

//            val movieDeferred = async { repository.getById(id) }
//            val relatedDeferred = async { repository.getList("Shrek").take(3) }
//
//            mutableState.movie = movieDeferred.await()
//            mutableState.related = relatedDeferred.await()
        }
    }

    fun back() {
        navigation.back()
    }

    fun onRatingChanged(rating: Float) {
        mutableState.rating = rating
    }

    fun onImageClick() {
        downloadImage(context, viewState.movie?.poster, "poster.jpg")
    }

    private fun downloadImage(context: Context, imageUrl: String?, fileName: String) {
        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val request = DownloadManager.Request(Uri.parse(imageUrl))

        val values = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
            put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
        }

        val uri = context.contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            values
        )

        request.setDestinationUri(uri)
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

        val downloadId = downloadManager.enqueue(request)

        val onComplete: BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
                if (id == downloadId) {
                    Toast.makeText(context, "Изображение загружено!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        context.registerReceiver(onComplete, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
    }

    private class MutableDetailsState : MovieDetailState {
        override var movie: MovieFullEntity? by mutableStateOf(null)
        override var rating: Float by mutableFloatStateOf(0f)
        override val isRatingVisible: Boolean get() = rating != 0f
        override var isLoading: Boolean by mutableStateOf(false)
        override var error: String? by mutableStateOf(null)
        override var related: List<MovieShortEntity> by mutableStateOf(emptyList())
    }
}