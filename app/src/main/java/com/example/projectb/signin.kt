package com.example.projectb

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.projectb.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class signin : Fragment() {
    private lateinit var binding: ActivityMainBinding
    lateinit var signin: Button
    lateinit var texttoup: TextView
    lateinit var emailin: TextInputEditText
    lateinit var passin: TextInputEditText
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val S= inflater.inflate(R.layout.fragment_signin, container, false)
        binding=ActivityMainBinding.inflate(layoutInflater)
        signin=S.findViewById(R.id.signin)
        texttoup=S.findViewById(R.id.texttoup)
        emailin=S.findViewById(R.id.emailin)
        passin=S.findViewById(R.id.passin)
        firebaseAuth = FirebaseAuth.getInstance()
        texttoup.setOnClickListener {
            shecvla(login())
        }


        signin.setOnClickListener {
            val email = emailin.text.toString()
            val pass = passin.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        activity?.recreate()
                        Toast.makeText(requireContext(), "successful", Toast.LENGTH_SHORT).show()

                    } else {
                        Toast.makeText(requireContext(), it.exception.toString(), Toast.LENGTH_SHORT).show()

                    }
                }
            } else {
                Toast.makeText(requireContext(), "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()

            }
        }
        return S
    }
    private fun shecvla(fragment: Fragment){
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.framelayout, fragment)
        transaction?.disallowAddToBackStack()
        transaction?.commit()
    }

}