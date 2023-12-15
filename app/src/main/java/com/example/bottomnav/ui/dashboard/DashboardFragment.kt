package com.example.bottomnav.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bottomnav.InventoryApplication
import com.example.bottomnav.ItemListAdapter
import com.example.bottomnav.data.model.Item
import com.example.bottomnav.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private val dashboardViewModel: DashboardViewModel by viewModels<DashboardViewModel> {
        InventoryViewModelFactory(
            (activity?.application as InventoryApplication)
                .database
                .itemDao()
        )
    }

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /*val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)*/

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)

        binding.btnPut.setOnClickListener { insertData() }
        binding.btnGet.setOnClickListener{findItem()}
        val adapter = ItemListAdapter {
        }
        binding.recyclerView.adapter = adapter

        dashboardViewModel.allItems.observe(this.viewLifecycleOwner) { items ->
            items.let {
                adapter.submitList(it)
            }
        }

       /* val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return root
    }

    private fun insertData() {
       var itemName = binding.etDb.text.toString()
        var item = Item(11,itemName,20.0,22)
        dashboardViewModel.insertItem(item)
    }

    private fun findItem() {
        dashboardViewModel.retrieveItem(binding.etDb.text.toString().toInt()).observe(this.viewLifecycleOwner) {
                foundItem -> binding.tvDb.text = foundItem.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}