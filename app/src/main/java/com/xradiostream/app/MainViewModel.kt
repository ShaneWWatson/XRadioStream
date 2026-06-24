package com.xradiostream.app

import android.content.ComponentName
import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.ListenableFuture

/**
 * ViewModel for the [MainActivity].
 * Manages the connection to the [PlaybackService] and holds the [MediaController] state.
 */
class MainViewModel : ViewModel() {

	companion object {
		private const val TAG = "MainViewModel"
	}

	private var controllerFuture: ListenableFuture<MediaController>? = null

	/**
	 * The [MediaController] used to interact with the playback service.
	 * This is null until the service is connected.
	 */
	var mediaController by mutableStateOf<MediaController?>(null)
		private set

	/**
	 * Establishes a connection to the [PlaybackService].
	 */
	fun connectToService(context: Context) {
		if (controllerFuture != null) return

		val sessionToken =
			SessionToken(context, ComponentName(context, PlaybackService::class.java))
		val future = MediaController.Builder(context, sessionToken).buildAsync()

		controllerFuture = future
		future.addListener(
			{
				try {
					mediaController = future.get()
				} catch (e: Exception) {
					Log.e(TAG, "Failed to connect to PlaybackService", e)
					// Clean up the future on failure to allow for retry attempts
					MediaController.releaseFuture(future)
					controllerFuture = null
				}
			},
			ContextCompat.getMainExecutor(context),
		                  )
	}

	/**
	 * Disconnects from the [PlaybackService] and releases the controller.
	 */
	fun releaseController() {
		controllerFuture?.let { future ->
			MediaController.releaseFuture(future)
			controllerFuture = null
			mediaController = null
		}
	}

	override fun onCleared() {
		super.onCleared()
		// Although the controller is usually released in Activity.onStop,
		// we release it here as a fallback.
		releaseController()
	}
}
