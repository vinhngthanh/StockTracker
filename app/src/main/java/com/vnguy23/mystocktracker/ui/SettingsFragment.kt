package com.vnguy23.mystocktracker.ui

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.vnguy23.mystocktracker.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {

    private lateinit var prefs: SharedPreferences
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("CommitPrefEdits")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        binding.apply {
            prefs = PreferenceManager.getDefaultSharedPreferences(binding.root.context)
            val editor: SharedPreferences.Editor = prefs.edit()

            if(prefs.getInt("CARD", 1) == 1){
                GreyCard.isChecked = true
            }else{
                RedCard.isChecked = true
            }

            if(prefs.getInt("CODE", 1) == 1){
                GreenCode.isChecked = true
            }else{
                YellowCode.isChecked = true
            }

            if(prefs.getInt("INPUT", 1) == 1){
                BlueInput.isChecked = true
            }else{
                PurpleInput.isChecked = true
            }

            if(prefs.getInt("DIALOG", 1) == 1){
                DialogOn.isChecked = true
            }else{
                DialogOff.isChecked = true
            }

            if(prefs.getInt("COMMENT", 1) == 1){
                AutoCommentOn.isChecked = true
            }else{
                AutoCommentOff.isChecked = true
            }

            CardSettingGroup.setOnCheckedChangeListener { _, _ ->
                if(GreyCard.isChecked){
                    editor.putInt("CARD", 1)
                    editor.apply()
                } else{
                    editor.putInt("CARD", 2)
                    editor.apply()
                }
            }
            CodeSettingGroup.setOnCheckedChangeListener { _, _ ->
                if(GreenCode.isChecked){
                    editor.putInt("CODE", 1)
                    editor.apply()
                } else{
                    editor.putInt("CODE", 2)
                    editor.apply()
                }
            }
            InputSettingGroup.setOnCheckedChangeListener { _, _ ->
                if(BlueInput.isChecked){
                    editor.putInt("INPUT", 1)
                    editor.apply()
                } else{
                    editor.putInt("INPUT", 2)
                    editor.apply()
                }
            }
            DialogSettingGroup.setOnCheckedChangeListener { _, _ ->
                if(DialogOn.isChecked){
                    editor.putInt("DIALOG", 1)
                    editor.apply()
                } else{
                    editor.putInt("DIALOG", 2)
                    editor.apply()
                }
            }
            CommentSettingGroup.setOnCheckedChangeListener { _, _ ->
                if(AutoCommentOn.isChecked){
                    editor.putInt("COMMENT", 1)
                    editor.apply()
                } else{
                    editor.putInt("COMMENT", 2)
                    editor.apply()
                }
            }

            settingsDoneButton.setOnClickListener {
                val action = SettingsFragmentDirections.actionSettingsFragmentToMainFragment()
                findNavController().navigate(action)
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }
}