package com.caron.instagram.screens.profilesettings

import com.caron.instagram.common.AuthManager
import com.caron.instagram.screens.common.BaseViewModel
import com.google.android.gms.tasks.OnFailureListener

class ProfileSettingsViewModel(private val authManager: AuthManager,
                               onFailureListener: OnFailureListener) :
        BaseViewModel(onFailureListener),
        AuthManager by authManager