package com.caron.instagram.screens

import android.app.Application
import com.caron.instagram.common.firebase.FirebaseAuthManager
import com.caron.instagram.data.firebase.FirebaseFeedPostsRepository
import com.caron.instagram.data.firebase.FirebaseNotificationsRepository
import com.caron.instagram.data.firebase.FirebaseSearchRepository
import com.caron.instagram.data.firebase.FirebaseUsersRepository
import com.caron.instagram.screens.notifications.NotificationsCreator
import com.caron.instagram.screens.search.SearchPostsCreator

class InstagramApp : Application() {
    val usersRepo by lazy { FirebaseUsersRepository() }
    val feedPostsRepo by lazy { FirebaseFeedPostsRepository() }
    val notificationsRepo by lazy { FirebaseNotificationsRepository() }
    val authManager by lazy { FirebaseAuthManager() }
    val searchRepo by lazy { FirebaseSearchRepository() }

    override fun onCreate() {
        super.onCreate()
        NotificationsCreator(notificationsRepo, usersRepo, feedPostsRepo)
        SearchPostsCreator(searchRepo)
    }
}