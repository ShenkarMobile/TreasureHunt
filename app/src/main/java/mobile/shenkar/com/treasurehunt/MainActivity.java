package mobile.shenkar.com.treasurehunt;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cadan.myapplication.backend.registration.Registration;
import com.example.cadan.myapplication.backend.treasureapi.Treasureapi;
import com.example.cadan.myapplication.backend.treasureapi.model.User;
import com.example.cadan.myapplication.backend.treasureapi.model.UserCollection;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Treasureapi treasureapi = new Treasureapi.Builder(
                AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
                new HttpRequestInitializer() {
                    public void initialize(HttpRequest httpRequest) {
                    }
                }).setRootUrl("https://absolute-shell-96315.appspot.com/_ah/api/").build();

        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    User u = new User();
                    u.setAddress("Tel Aviv");
                    u.setFullName("Moshe Cohen");
                    User user = treasureapi.insertUser(u).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

        }.execute();


//
//
//        // Start IntentService to register this application with GCM.
//        Intent intent = new Intent(this, RegistrationIntentService.class);
//        startService(intent);
    }
}
