package will.shiro.forecast.forecast.firebase

import com.google.cloud.firestore.Firestore
import com.google.firebase.cloud.FirestoreClient
import org.springframework.stereotype.Component

@Component
class FirestoreInstance {

    fun get(): Firestore {
        return FirestoreClient.getFirestore()
    }
}