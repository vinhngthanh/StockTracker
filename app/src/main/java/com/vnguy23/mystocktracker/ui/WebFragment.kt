package com.vnguy23.mystocktracker.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.vnguy23.mystocktracker.R
import com.vnguy23.mystocktracker.databinding.FragmentInfoBinding
import com.vnguy23.mystocktracker.databinding.FragmentWebBinding

private var _binding: FragmentWebBinding? = null
private val binding get() = _binding!!

class WebFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = FragmentWebBinding.inflate(inflater, container, false)
        binding.apply {

        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}