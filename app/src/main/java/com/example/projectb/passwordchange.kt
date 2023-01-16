package com.example.projectb

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
import org.w3c.dom.Text

class passwordchange : Fragment() {
    lateinit var oldpass:TextInputEditText
    lateinit var newpass:TextInputEditText
    lateinit var confirmnewpass:TextInputEditText
    lateinit var change:Button
    lateinit var cancel:TextView
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val P = inflater.inflate(R.layout.fragment_passwordchange, container, false)
        firebaseAuth=FirebaseAuth.getInstance()
        change=P.findViewById(R.id.passchange)
        oldpass=P.findViewById(R.id.oldpass)
        newpass=P.findViewById(R.id.newpass)
        confirmnewpass=P.findViewById(R.id.confirmNewPass)
        cancel=P.findViewById(R.id.Cancel)
        change.setOnClickListener {
            if (oldpass.text?.length!=0 && newpass.text?.length!=0 && confirmnewpass.text?.length!=0){
                if (newpass.text.toString()==confirmnewpass.text.toString()){
                    val user=firebaseAuth.currentUser
                    user?.updatePassword(newpass.text.toString())?.addOnCompleteListener{ task ->
                        if (task.isSuccessful){
                            Toast.makeText(requireContext(), "Password changed successfully", Toast.LENGTH_SHORT).show()
                            activity?.recreate()
                        }
                    }


                }
                else{
                    Toast.makeText(requireContext(), "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()
                }
            }
        }
        cancel.setOnClickListener {
            activity?.recreate()
        }
        return P
    }

}