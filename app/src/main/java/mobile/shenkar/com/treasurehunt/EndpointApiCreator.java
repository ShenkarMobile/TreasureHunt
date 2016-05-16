package mobile.shenkar.com.treasurehunt;

import com.example.cadan.myapplication.backend.registration.Registration;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.util.HashMap;

/*
 * This class response for the creation of the endpoints object
 * In case of new enpoint, this class must be updated.
 * 
 */
@SuppressWarnings("rawtypes")
public class EndpointApiCreator {
    private static GoogleAccountCredential credential;
    // map of all the endpoints that was created.
    private static HashMap<Class, AbstractGoogleJsonClient> endpointsList;
    static {
        endpointsList = new HashMap<Class, AbstractGoogleJsonClient>();
    }

    public static void setCradential(GoogleAccountCredential cred)
    {
        credential = cred;
    }

    @SuppressWarnings("unchecked")
    public static <T extends AbstractGoogleJsonClient> T getApi(
            Class<T> endpointClass) throws Exception {
        if (endpointsList.containsKey(endpointClass))
            return (T) endpointsList.get(endpointClass);
        // The builder.
        T.Builder endpointBuilder = null;


        //check for the relevant builder by the class type.
        if (Registration.class.isAssignableFrom(endpointClass)) {
            endpointBuilder = new Registration.Builder(
                    AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
                    new HttpRequestInitializer() {
                        public void initialize(HttpRequest httpRequest) {
                        }
                    });
        }
        // build the endpoint and save to the map obj.
        T ret = (T) CloudEndpointUtils.updateBuilder(endpointBuilder).build();
        endpointsList.put(endpointClass, ret);
        return ret;
    }
}
