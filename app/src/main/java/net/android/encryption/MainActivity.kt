package net.android.encryption

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val masterKeys = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        val sharedPreferences = EncryptedSharedPreferences.create(
            "FILE_NAME",
            masterKeys,
            applicationContext,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
        val edit = sharedPreferences.edit()
        encryptBtn.setOnClickListener {
            edit.putString("key", encryptText.text.toString())
            edit.apply()
        }
        decryptBtn.setOnClickListener {
            val decrypt = sharedPreferences.getString("key", "")
            decryptText.text = decrypt
        }
    }
}