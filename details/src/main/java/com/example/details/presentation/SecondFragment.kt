package com.example.details.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.details.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("name")?.let {
            binding.tvName.text = it
        }
        arguments?.getString("country")?.let {
            binding.tvCountry.text = it
        }
        arguments?.getString("alphaTwoCode")?.let {
            binding.tvAlphaTwoCode.text = it
        }

        arguments?.getString("state")?.let {
            binding.tvStateProvince.text = it
        }

        arguments?.getStringArrayList("domains")?.let {
            binding.tvDomains.text = it.joinToString()
        }

        arguments?.getStringArrayList("web")?.let {
            binding.rvWebPages.text = it.joinToString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}