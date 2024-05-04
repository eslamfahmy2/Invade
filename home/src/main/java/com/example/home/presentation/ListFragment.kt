package com.example.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.home.R
import com.example.home.databinding.FragmentFirstBinding

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ListFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel2 by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { state ->
                when (state) {
                    is ListFragmentState.Error -> {
                        binding.loading.visibility = View.GONE
                        Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
                    }

                    ListFragmentState.Loading -> {
                        binding.loading.visibility = View.VISIBLE
                    }

                    is ListFragmentState.Success -> {
                        binding.loading.visibility = View.GONE
                        val adapter = UniversityAdapter(state.data, onClick = {
                            val bundle = Bundle()
                            bundle.putString("name", it.name)
                            bundle.putString("country", it.country)
                            bundle.putString("alphaTwoCode", it.alphaTwoCode)
                            bundle.putString("state", it.state)
                            bundle.putStringArrayList("domains", ArrayList(it.domains))
                            bundle.putStringArrayList("web", ArrayList(it.web))
                            findNavController().navigate(R.id.toDetails, bundle)
                        })
                        binding.rvList.layoutManager =
                            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                        binding.rvList.adapter = adapter
                    }
                }
            }
        }
        binding.btnLan.setOnClickListener {
            viewModel.onSearch()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.onSearch()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

