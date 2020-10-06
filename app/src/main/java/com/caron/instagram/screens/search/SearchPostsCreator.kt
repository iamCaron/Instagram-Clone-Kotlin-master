package com.caron.instagram.screens.search

import android.arch.lifecycle.Observer
import android.util.Log
import com.caron.instagram.common.BaseEventListener
import com.caron.instagram.common.Event
import com.caron.instagram.common.EventBus
import com.caron.instagram.data.SearchRepository
import com.caron.instagram.models.SearchPost

class SearchPostsCreator(searchRepo: SearchRepository) : BaseEventListener() {
    init {
        EventBus.events.observe(this, Observer {
            it?.let { event ->
                when (event) {
                    is Event.CreateFeedPost -> {
                        val searchPost = with(event.post) {
                            SearchPost(
                                    image = image,
                                    caption = caption,
                                    postId = id)
                        }
                        searchRepo.createPost(searchPost).addOnFailureListener {
                            Log.d(TAG, "Failed to create search post for event: $event", it)
                        }
                    }
                    else -> {
                    }
                }
            }
        })
    }

    companion object {
        const val TAG = "SearchPostsCreator"
    }
}