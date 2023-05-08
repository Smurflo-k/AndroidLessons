package ru.mirea.shurchkov.securesharedpreferences;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class SecureSharedPreferencesMainActivity extends AppCompatActivity {

    private SharedPreferences secureSharedPreferences;
    private String hideName;
    private ImageView photoView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secure_shared_preferences_main);
        try {
            KeyGenParameterSpec keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC;
            String mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec);
            secureSharedPreferences = EncryptedSharedPreferences.create(
                    "secret_shared_prefs",
                    mainKeyAlias,
                    getBaseContext(),
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
            secureSharedPreferences.edit().putString("secure", "Стюарт Бенедикт Камбербетович").apply();
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }

        hideName = secureSharedPreferences.getString("secure", "Стюарт Бенедикт Камбербетович)");
        textView = findViewById(R.id.textView);
        textView.setText(hideName);
        photoView = findViewById(R.id.photoView);
    }
}