package com.victor.task.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.firebase.auth.FirebaseAuth
import com.victor.task.R
import com.victor.task.databinding.FragmentLoginBinding
import com.victor.task.databinding.FragmentRecoverAccountBinding
import com.victor.task.util.initToolbar
import com.victor.task.util.showBottomSheet

class RecoverAccountFragment : Fragment() {

    private var _binding: FragmentRecoverAccountBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecoverAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()

        initToolbar(binding.toolbar)
        initListener()
    }

    private fun initListener() {
        binding.botaoRedefinir.setOnClickListener {
            validateData()
        }
    }

    private fun validateData() {
        val email = binding.emailInput.text.toString().trim()

        if (email.isNotBlank()){
            recoverAccountUser(email)
        } else {
            showBottomSheet(message = getString(R.string.email_empty))
        }
    }

    private fun recoverAccountUser(email: String) {
        binding.progressBar.isVisible = true

        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                binding.progressBar.isVisible = false

                if (task.isSuccessful) {
                    showBottomSheet(
                        message = ""
                    )
                } else {
                    val error = task.exception?.message ?: "Ocorreu um erro ao enviar o e-mail."
                    showBottomSheet(message = error)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}