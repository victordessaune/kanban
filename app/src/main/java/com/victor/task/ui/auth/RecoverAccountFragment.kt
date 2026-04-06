package com.victor.task.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.victor.task.R
import com.victor.task.databinding.FragmentLoginBinding
import com.victor.task.databinding.FragmentRecoverAccountBinding
import com.victor.task.util.initToolbar
import com.victor.task.util.showBottomSheet

class RecoverAccountFragment : Fragment() {

    private var _binding: FragmentRecoverAccountBinding? = null
    private val binding get() = _binding!!

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
            Toast.makeText(requireContext(), "Tudo OK!", Toast.LENGTH_SHORT).show()
        } else {
            showBottomSheet(message = getString(R.string.email_empty))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}