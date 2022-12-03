package com.jasmeet.roomdb.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jasmeet.roomdb.databinding.FragmentServiceBinding

class ServiceFragment : Fragment() {

    private lateinit var _binding: FragmentServiceBinding
    private val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentServiceBinding.inflate(layoutInflater)

        binding.button.setOnClickListener {
            val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            startActivity(intent)
        }
        return binding.root
    }

}