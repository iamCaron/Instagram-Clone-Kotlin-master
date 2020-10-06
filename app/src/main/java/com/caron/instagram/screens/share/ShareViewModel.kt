package com.caron.instagram.screens.share

import android.net.Uri
import com.caron.instagram.common.SingleLiveEvent
import com.caron.instagram.data.FeedPostsRepository
import com.caron.instagram.data.UsersRepository
import com.caron.instagram.models.FeedPost
import com.caron.instagram.models.User
import com.caron.instagram.screens.common.BaseViewModel
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.Tasks

class ShareViewModel(private val feedPostsRepo: FeedPostsRepository,
                     private val usersRepo: UsersRepository,
                     onFailureListener: OnFailureListener) : BaseViewModel(onFailureListener) {
    private val _shareCompletedEvent = SingleLiveEvent<Unit>()
    val shareCompletedEvent = _shareCompletedEvent
    val user = usersRepo.getUser()

    fun share(user: User, imageUri: Uri?, caption: String) {
        if (imageUri != null) {
            usersRepo.uploadUserImage(user.uid, imageUri).onSuccessTask { downloadUrl ->
                Tasks.whenAll(
                        usersRepo.setUserImage(user.uid, downloadUrl!!),
                        feedPostsRepo.createFeedPost(user.uid, mkFeedPost(user, caption,
                                downloadUrl.toString()))
                )
            }.addOnCompleteListener{
                _shareCompletedEvent.call()
            }.addOnFailureListener(onFailureListener)
        }
    }

    private fun mkFeedPost(user: User, caption: String, imageDownloadUrl: String): FeedPost {
        return FeedPost(
                uid = user.uid,
                username = user.username,
                image = imageDownloadUrl,
                caption = caption,
                photo = user.photo
        )
    }
}