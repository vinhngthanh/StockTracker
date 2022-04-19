package com.vnguy23.mystocktracker.ui.webview

import android.os.Bundle
import android.view.*
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vnguy23.mystocktracker.databinding.FragmentWebBinding

class WebFragment : Fragment() {
    private var _binding: FragmentWebBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WebviewViewModel by lazy {
        ViewModelProvider(this)[WebviewViewModel::class.java]
    }
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
            webView.webViewClient = WebViewClient()

            viewModel.url.observe(viewLifecycleOwner) {
                gobar.urlEditText.setText(it)
                loadUrl(it)
            }

            gobar.urlEditText.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                    viewModel.setUrl(gobar.urlEditText.text.toString())
                    return@OnKeyListener true
                }
                false
            })
            gobar.goButton.setOnClickListener {
                viewModel.setUrl(gobar.urlEditText.text.toString())
            }
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

    private fun loadUrl(request: String) {
        binding.webView.loadUrl(request)
    }
}