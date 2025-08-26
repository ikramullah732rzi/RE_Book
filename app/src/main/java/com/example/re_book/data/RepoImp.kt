package com.example.re_book.data

import android.util.Log
import com.example.re_book.domain.Repo
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.util.UUID
import javax.inject.Inject

class RepoImp @Inject constructor(private val firebaseDatabase: FirebaseDatabase)    : Repo {
    override suspend fun getAllBooks(): Flow<NetworkState<List<Model>>> {
        return callbackFlow {
            trySend(NetworkState.Loading)
            val valueEvent = object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val bookList = snapshot.children.map {
                    it.getValue(Model::class.java)!!
                    }
                    trySend(NetworkState.Success(bookList))
                }
                override fun onCancelled(error: DatabaseError) {
                    trySend(NetworkState.Error(message = error.message))
                }
            }
            firebaseDatabase.reference.child(BOOKS).addValueEventListener(valueEvent)

            awaitClose {
                firebaseDatabase.reference.removeEventListener(valueEvent)
            }
        }
    }


        fun createDB(model: Model) {
            val id = UUID.randomUUID().toString()


            firebaseDatabase.reference.child(BOOKS).child(id)
                .setValue(model)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Log.w("debug", "✅ Data added successfully: $id")
                    } else {
                        Log.e("debug", "❌ Failed to add data")
                    }
                }
        }
}
