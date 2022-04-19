package com.vnguy23.mystocktracker.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.vnguy23.mystocktracker.R
import com.vnguy23.mystocktracker.databinding.FragmentInfoBinding


class InfoFragment : Fragment() {

    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!
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
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        binding.apply {
            val applicationInfo = requireContext().applicationInfo
            val stringId = applicationInfo.labelRes
            appName.setText(context?.getString(stringId) ?: String())

            val versionName = requireContext().packageManager.getPackageInfo(requireContext().packageName, 0).versionName
            appVersion.text = getString(R.string.version, versionName)
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