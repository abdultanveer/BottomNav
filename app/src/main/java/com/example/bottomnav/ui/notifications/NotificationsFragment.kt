package com.example.bottomnav.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bottomnav.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
            notificationsViewModel.startTime()
            notificationsViewModel.seconds().observe(viewLifecycleOwner) {
                textView.text = it.toString()
            }
            binding.tvCounter.text = notificationsViewModel.number.toString()


            binding.btnAdd.setOnClickListener {
                //notificationsViewModel.addNumber()
                binding.tvCounter.text = notificationsViewModel.number.toString()
                notificationsViewModel.startTime()
                // dashboardViewModel.addNumber()
                //binding.tvCounter.text = dashboardViewModel.number.toString()
            }

        }
        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    //abdul.com/a=10,b=20,op=mul
    //{ mul = 200}
    //{ sum =  30}

    fun add(a:Int, b:Int):Int{
        return a+b
    }
}