package com.example.testmap.filter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testmap.R
import com.example.testmap.SharedViewModel
import com.example.testmap.databinding.FragmentFilterBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class FilterFragment : Fragment() {

    private var _binding: FragmentFilterBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFilterBinding.inflate(inflater, container, false)

        val recyclerView: RecyclerView = binding.servicesList
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        val sharedViewModel = this.activityViewModels<SharedViewModel>().value

        sharedViewModel.root.observe(viewLifecycleOwner) {
            recyclerView.adapter = ServiceRecyclerViewAdapter(it.services)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSubmit.setOnClickListener {
            findNavController().navigate(R.id.action_FilterFragment_to_MapFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}