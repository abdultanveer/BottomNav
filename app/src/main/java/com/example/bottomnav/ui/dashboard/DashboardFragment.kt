package com.example.bottomnav.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.bottomnav.InventoryApplication
import com.example.bottomnav.data.Item
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

        binding.btnPut.setOnClickListener { insertData() }
        binding.btnGet.setOnClickListener{findItem()}

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