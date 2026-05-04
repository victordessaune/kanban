package com.victor.task.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.victor.task.R
import com.victor.task.databinding.FragmentRegisterBinding
import com.victor.task.util.initToolbar
import com.victor.task.util.showBottomSheet


class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    // Variável que armazena a API de autenticação com o Firebase Auth
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(binding.toolbar)
        initListener()
    }

    private fun initListener(){
        binding.cadastroBotao.setOnClickListener{
            validateData()
        }
    }

    private fun validateData() {
        val email = binding.emailInput.text.toString().trim()
        val senha = binding.senhaInput.text.toString().trim()

        if (email.isNotBlank()){
            if (senha.isNotBlank()){
                Toast.makeText(requireContext(), "Tudo OK!", Toast.LENGTH_SHORT).show()
            } else {
                showBottomSheet(message = getString(R.string.password_empty_register_fragment))
            }
        } else {
            showBottomSheet(message = getString(R.string.email_empty_register_fragment))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}