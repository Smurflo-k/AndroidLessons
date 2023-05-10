package ru.mirea.shurchkov.mireaproject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import ru.mirea.shurchkov.firebaseauth.databinding.EnterpasactivityBinding;

public class EnterPas extends AppCompatActivity {

    private static final String TAG = EnterPas.class.getSimpleName();
    private EnterpasactivityBinding binding;
    // START declare_auth
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = EnterpasactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        binding.signinbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn(binding.editTextTextEmailAddress.getText().toString(), binding.editTextTextPassword.getText().toString());
            }
        });
        binding.createaccountbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount(binding.editTextTextEmailAddress.getText().toString(), binding.editTextTextPassword.getText().toString());
            }
        });
        binding.signoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
        binding.verifyEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmailVerification();
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
    private void updateUI(FirebaseUser user) {
        if (user != null) {

            binding.statusTextView.setText(getString(R.string.emailpassword_status_fmt,

                    user.getEmail(), user.isEmailVerified()));

            binding.detailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));


            binding.verifyEmailButton.setEnabled(!user.isEmailVerified());
        } else {
            binding.statusTextView.setText(R.string.signed_out);
            binding.detailTextView.setText(null);
        }
    }
    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {

                            Log.w(TAG, "createUserWithEmail:failure",

                                    task.getException());
                            Toast.makeText(EnterPas.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }
    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {

                            Log.w(TAG, "signInWithEmail:failure", task.getException());

                            Toast.makeText(EnterPas.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        if (!task.isSuccessful()) {

                            binding.statusTextView.setText(R.string.auth_failed);
                        }
                    }
                });
    }
    private void signOut() {
        mAuth.signOut();
        updateUI(null);
    }
    private void sendEmailVerification() {
        binding.verifyEmailButton.setEnabled(false);
        final FirebaseUser user = mAuth.getCurrentUser();
        Objects.requireNonNull(user).sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override

                    public void onComplete(@NonNull Task<Void> task) {
                        binding.verifyEmailButton.setEnabled(true);

                        if (task.isSuccessful()) {
                            Toast.makeText(EnterPas.this,
                                    "Verification email sent to " + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();
                        } else {

                            Log.e(TAG, "sendEmailVerification", task.getException());

                            Toast.makeText(EnterPas.this,
                                    "Failed to send verification email.",

                                    Toast.LENGTH_SHORT).show();

                        }
                    }

                });
    }
}
