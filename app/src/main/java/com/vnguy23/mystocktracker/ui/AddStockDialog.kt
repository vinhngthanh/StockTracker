package com.vnguy23.mystocktracker.ui

import android.app.AlertDialog
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.vnguy23.mystocktracker.R
import com.vnguy23.mystocktracker.database.Stock
import com.vnguy23.mystocktracker.databinding.AddFragmentBinding
import com.vnguy23.mystocktracker.ui.main.MainViewModel
import java.util.*

class AddStockDialog : BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener {

    private val sharedViewModel: MainViewModel by activityViewModels()
    private var _binding: AddFragmentBinding? = null
    private val binding get() = _binding!!
    private var newStock = Stock()
    private lateinit var prefs: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddFragmentBinding.inflate(inflater, container, false)
        binding.apply {

            prefs = PreferenceManager.getDefaultSharedPreferences(binding.root.context)
            if(prefs.getInt("COMMENT", 1) == 2){
                commentEditText.isFocusableInTouchMode = true
                commentEditText.isFocusable = true
                commentEditText.isEnabled = true
            }else{
                commentEditText.isFocusableInTouchMode = false
                commentEditText.isFocusable = false
                commentEditText.isEnabled = false
            }

            addButton.setOnClickListener {
                with(newStock) {
                    stock_code = stockCodeEditText.text.toString().uppercase(Locale.getDefault())
                    amount = amountEditText.text.toString()
                    current_price = getString(R.string.dollar) + currentPriceEditText.text.toString()
                    bought_price = getString(R.string.dollar) + boughtPriceEditText.text.toString()
                    if(prefs.getInt("COMMENT", 1) == 2){
                        comment = commentEditText.text.toString()
                    }else{
                        val currPrice = Integer.parseInt(currentPriceEditText.text.toString())
                        val bouPrice = Integer.parseInt(boughtPriceEditText.text.toString())
                        val amt = Integer.parseInt(amountEditText.text.toString())
                        val profit = (currPrice - bouPrice) * amt

                        if(profit > 0){
                            comment = getString(R.string.gain) + " " + getString(R.string.dollar) + profit.toString()
                        }else if(profit < 0){
                            comment = getString(R.string.loss) + " "  + getString(R.string.sign) + getString(R.string.dollar) + (profit*(-1)).toString()
                        }else{
                            comment = getString(R.string.draw) + " "  + getString(R.string.dollar) + profit.toString()
                        }
                    }
                }
                sharedViewModel.addStock(newStock)
                if(prefs.getInt("DIALOG", 1) == 1){
                    stockAddedAlert(newStock)
                }
                dismiss()
            }
            cancelButton.setOnClickListener {
                dismiss()
            }
        }
        return binding.root
    }

    private fun stockAddedAlert(newStock: Stock) {
        val msg = resources.getString(R.string.stock_added_alert, newStock.stock_code + " " + newStock.amount)
        val builder = AlertDialog.Builder(context)
        with(builder) {
            setTitle(R.string.congrat)
            setMessage(msg)
            setIcon(R.drawable.ic_baseline_notifications_active_24)
            setPositiveButton(R.string.ok, null)
            show()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

}