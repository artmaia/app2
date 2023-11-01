package com.example.appreservas.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.appreservas.R
import com.example.appreservas.databinding.FragmentRecorverAccountBinding
import com.example.appreservas.helper.FirebaseHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RecorverAccountFragment : Fragment() {

    private var _binding: FragmentRecorverAccountBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecorverAccountBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        initClicks()
    }

    private fun initClicks(){
        binding.btnSend.setOnClickListener{
            ValidateData()
        }
    }

    private fun ValidateData(){
        val email = binding.editEmail.text.toString().trim()

        if (email.isNotEmpty()) {
            binding.progressbar.isVisible = true
            recoverAccountUser(email)
        }else{
            Toast.makeText(requireContext(), "Informe seu email.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun recoverAccountUser(email:String){
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(requireContext(), "Pronto, acabamos de enviar um link para seu email", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), FirebaseHelper.validError(task.exception?.message ?: ""), Toast.LENGTH_SHORT).show()
                }
                binding.progressbar.isVisible = false
            }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}