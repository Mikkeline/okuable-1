package com.madassignment.okuable.activity

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.madassignment.okuable.R
import com.madassignment.okuable.fragment.*


class MainActivity3 : AppCompatActivity() {

    private val eventFragment = EventFragment_Admin()
    private val caregiverFragmentAdmin = CaregiverFragment_Admin()
    private val caregiverfragmentPublic = CaregiverFragment_Public()
    private val caregiverfragmentUser = CaregiverFragment_User()

    private val profileFragment = ProfileFragment()

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        replaceFragment(eventFragment)

        val guserID = "GQWDqnWFXwPFMETNrAqngg2Q1xo1" //caregiver
        val ruserID = "bKINQx9iGgd6vFQ2hSikc3zGxQt2" //carereceiver

        val database = Firebase.database
        val myRef = database.getReference("Test")

        val bottom_nav: BottomNavigationView = findViewById(R.id.bottomNav)

        var uid = ""

        //myRef.setValue("Hello, World!")

        var userType = ""
        auth = Firebase.auth

        /*auth.signInWithEmailAndPassword("michellewong1029@gmail.com", "123456")
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    uid = user?.uid!!
                    // updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }*/

        bottom_nav.setOnNavigationItemSelectedListener { item ->

            when (item.itemId) {
                R.id.ic_event -> {
                    replaceFragment(eventFragment)
                }
                R.id.ic_caregiver -> {
                    replaceFragment(caregiverFragmentAdmin)
                }
                R.id.ic_profile -> {
                    replaceFragment(profileFragment)
                }
            }
            true
        }
/*
        myRef.child(guserID).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                val ut = snapshot.child("userType").value.toString()
                if (ut == "Caregiver"){

                    bottom_nav.setOnNavigationItemSelectedListener { item ->

                        when (item.itemId) {
                            R.id.ic_event -> {
                                replaceFragment(eventFragment)
                            }
                            R.id.ic_caregiver -> {
                                replaceFragment(caregiverfragmentPublic)
                            }
                            R.id.ic_profile -> {
                                replaceFragment(profileFragment)
                            }
                        }
                        true
                    }


                }else if (ut == "Carereceiver"){


                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "dbretrievefail:failure", error.toException())
            }


        })*/



        //val database = Firebase.database
        // val myRef = database.getReference("Users").child("KqwNtEpvjgdO8lpLfBXEcu8QdAI2")



    }

    private fun replaceFragment(fragment: Fragment){
        if (fragment!=null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fcv_main, fragment)
            transaction.commit()
        }

    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(this.currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

}