package com.vnguy23.mystocktracker.ui.main

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vnguy23.mystocktracker.R
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vnguy23.mystocktracker.database.Stock
import com.vnguy23.mystocktracker.ui.StockAdapter
import com.vnguy23.mystocktracker.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var prefs: SharedPreferences
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private val stockAdapter = StockAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            prefs = PreferenceManager.getDefaultSharedPreferences(binding.root.context)
            stockRecycler.run {
                layoutManager = LinearLayoutManager(context)
                adapter = stockAdapter
            }

            addStock.setOnClickListener {
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToAddFriendDialog())
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.mediaLiveData.observe(viewLifecycleOwner) {
            stockAdapter.updateItems(it)
        }

        val itemTouchHelperCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ) = false

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val thisStock = stockAdapter.getStockAtPosition(viewHolder.adapterPosition)
                    if(prefs.getInt("DIALOG",1) == 1) {
                        friendDeletedAlert(thisStock)
                    }else{
                        viewModel.deleteStock(thisStock)
                    }
                }
            }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.stockRecycler)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun friendDeletedAlert(stock: Stock) {
        val msg = resources.getString(R.string.stock_deleted_alert, stock.stock_code + " " + stock.amount)
        val builder = AlertDialog.Builder(context)
        with(builder) {
            setTitle(R.string.alert)
            setMessage(msg)
            setIcon(R.drawable.ic_baseline_notifications_active_24)
            setPositiveButton(R.string.yes) { _, _ ->
                viewModel.deleteStock(stock)
            }
            setNegativeButton(R.string.no) { _, _ ->
                stockAdapter.notifyDataSetChanged()
            }
            show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}