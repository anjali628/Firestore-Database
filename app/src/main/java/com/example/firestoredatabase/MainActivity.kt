package com.example.firestoredatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firestoredatabase.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
           var FirstName= binding.firstName.text.toString().trim()
           var LastName= binding.lastName.text.toString().trim()
            var Age=binding.age.text.toString().trim()


            val user = HashMap<String,Any>() //define empty hashmap
            user.put("First Name",FirstName)
            user.put("Last Name",LastName)
            user.put("Age",Age)

            val db=FirebaseFirestore.getInstance()
            db.collection("user")
                .add(user)
                .addOnSuccessListener {documentReference ->
                    Toast.makeText(applicationContext,"Successfully added with ID:${documentReference.id}",Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {e->
                    Toast.makeText(applicationContext,"Failed: ${e.message}",Toast.LENGTH_SHORT).show()
                }

        }

    }
}