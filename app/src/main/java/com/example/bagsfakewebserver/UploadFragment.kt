package com.example.bagsfakewebserver

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.bagsfakewebserver.MainActivity.Companion.TAG
import com.example.bagsfakewebserver.databinding.FragmentUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class UploadFragment : Fragment() {
    private lateinit var binding: FragmentUploadBinding
    private lateinit var database: DatabaseReference
    private lateinit var categoryWomenReference: DatabaseReference
    private lateinit var categoriesReference: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_upload, container, false)
        database = Firebase.database.reference
        categoryWomenReference = database.child("categoryWomen")
        categoriesReference = database.child("categories")
        binding.btnGenerateKeys.setOnClickListener {
            var key1: String?
            listOfBags.forEach {
                key1 = categoryWomenReference.push().key
                it.id_product = key1
            }
        }
        binding.btnUpload.setOnClickListener {
            listOfBags.forEach {
                it.id_product?.let { it1 -> categoryWomenReference.child(it1).setValue(it) }
            }
        }
        binding.btnUploadCategories.setOnClickListener {
            listOfCategories.forEach {
                categoriesReference.child(it.category_id).setValue(it)
            }
        }
        binding.btnDelete.setOnClickListener {

            try {

                database.child("favorites").child("1Ro5oh34TwZYQQ6cRZYHonmsgHk1")
                    .child("-MkDLsEbLB3-c64vzCO5").setValue(null)
                Log.d(TAG, "button CLicked")
            }catch (e:Exception){
                Log.d(TAG, "button CLicked : exception : ${e.message}")
            }

        }
        // Inflate the layout for this fragment
        return binding.root
    }
}