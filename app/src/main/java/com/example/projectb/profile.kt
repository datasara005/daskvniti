package com.example.projectb

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.google.firebase.auth.FirebaseAuth


class profile : Fragment() {
    lateinit var username:TextView
    lateinit var Logout:TextView
    lateinit var changepass:TextView
    lateinit var emailshow:TextView
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val B = inflater.inflate(R.layout.fragment_profile, container, false)
        val viewPager:ViewPager2=B.findViewById(R.id.viewpager)
        val fragments= arrayListOf<Fragment>(
            ad2(),
            ad1()
        )
        val adapter=ViewPagerAdapter(fragments,this)
        viewPager.adapter=adapter
        firebaseAuth=FirebaseAuth.getInstance()
        username = B.findViewById(R.id.username)
        Logout=B.findViewById(R.id.Logout)
        emailshow=B.findViewById(R.id.emailshow)
        changepass=B.findViewById(R.id.changepass)
        emailshow.text=firebaseAuth.currentUser?.email.toString()
        loadData()
        Logout.setOnClickListener {
            firebaseAuth.signOut()
            Toast.makeText(requireContext(), "Signed out", Toast.LENGTH_SHORT).show()
            activity?.recreate()
        }
        changepass.setOnClickListener {
            shecvla(passwordchange())
        }
        username.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.edittext, null)
            val editText = dialogLayout.findViewById<EditText>(R.id.et_editText)
            with(builder) {
                setTitle("Enter new Username")
                setPositiveButton("change") { dialog, which ->
                    username.text = editText.text.toString()
                    saveData()
                }
                setNegativeButton("Cancel"){diaog,which->
                    Log.d("Main","Cenceled")
                }
                setView(dialogLayout)
                show()
            }

        }
        return B

    }
    private fun shecvla(fragment: Fragment){
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.framelayout, fragment)
        transaction?.disallowAddToBackStack()
        transaction?.commit()
    }
    private fun saveData(){
        val sharedPreference =  activity?.getSharedPreferences("username", Context.MODE_PRIVATE)
        var editor = sharedPreference?.edit()
        editor?.apply {
            putString("STRING_KEY",username.text.toString())
        }?.apply()
    }
    private fun loadData(){
        val sharedPreferences=activity?.getSharedPreferences("username",Context.MODE_PRIVATE)
        val savedString=sharedPreferences?.getString("STRING_KEY",null)
        if (savedString.toString().length>0){
            username.text=savedString
        }
    }


}
