package will.shiro.forecast.forecast

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.cloud.FirestoreClient
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct


@Service
class FirebaseInit {

    @PostConstruct
    fun init() {
        try {
            val options: FirebaseOptions = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.getApplicationDefault())
                .build()

            FirebaseApp.initializeApp(options)

            val db = FirestoreClient.getFirestore()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}