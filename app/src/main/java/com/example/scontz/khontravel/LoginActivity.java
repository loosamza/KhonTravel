package com.example.scontz.khontravel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.Gson;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;
    private Profile profile;
    Control_Database objControl_Database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        objControl_Database = new Control_Database(this);

        callbackManager = CallbackManager.Factory.create();

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {


            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                setProfile(newProfile);
            }
        };

        accessTokenTracker.startTracking();
        profileTracker.startTracking();


        loginButton = (LoginButton) findViewById(R.id.loginButton);

        loginButton.setReadPermissions(Arrays.asList("email"));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken accessToken = loginResult.getAccessToken();
                Profile profile = Profile.getCurrentProfile();
                setProfile(profile);
               /* try {
                    long insertvalue = objControl_Database.addValueToUID(profile.getId());
                } catch (Exception e) {
                    Log.d("DB", "Error Up Value ===> " + e.toString());
                }*/


                // goMainScreen();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "Cancel Login", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                if (error instanceof FacebookAuthorizationException) {
                    if (AccessToken.getCurrentAccessToken() != null) {
                      //  LoginManager.getInstance().logOut();
                    }
                }
            }

        });
    }

    @Override
    public void onStop() {
        super.onStop();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }

    @Override
    public void onResume() {
        super.onResume();
       // Profile profile = Profile.getCurrentProfile();
       // setProfile(profile);
    }

    private void setProfile(Profile profile) {

        profile = Profile.getCurrentProfile();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("profile", profile);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);


    }

    private void goMainScreen() {

       /* //Creating a shared
        SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);

        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(profile);
        prefsEditor.putString("profile", json);
        prefsEditor.commit();
*/

        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra("profile", profile);
        //String nameUser = profile.getFirstName() + " " + profile.getLastName();
        //String url = "" + profile.getProfilePictureUri(130, 100);
        //intent.putExtra("nameUser", nameUser);
        //intent.putExtra("url", url);


        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        goMainScreen();
    }


}
