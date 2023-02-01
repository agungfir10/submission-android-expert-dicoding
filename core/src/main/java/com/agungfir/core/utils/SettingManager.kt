package com.agungfir.core.utils

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.securepreferences.SecurePreferences

class SettingManager(context: Context) {
    companion object {
        const val KEY_DARK_MODE = "isLogin"
    }

    private var pref: SharedPreferences = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
        val spec = KeyGenParameterSpec.Builder(
            MasterKey.DEFAULT_MASTER_KEY_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setKeySize(MasterKey.DEFAULT_AES_GCM_MASTER_KEY_SIZE)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .build()
        val masterKey = MasterKey.Builder(context)
            .setKeyGenParameterSpec(spec)
            .build()
        EncryptedSharedPreferences
            .create(
                context,
                "Setting",
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
    } else {
        SecurePreferences(context, "veemoovsecret", "Setting")
    }

    private var editor: SharedPreferences.Editor = pref.edit()

    fun toggleDarkMode() {
        editor.putBoolean(KEY_DARK_MODE, !isDarkMode())
            .commit()
    }

    fun isDarkMode(): Boolean = pref.getBoolean(KEY_DARK_MODE, false)



}