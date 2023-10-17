package com.tk.cicdcourseexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import com.tk.cicdcourseexample.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
    }

    private fun setupListeners() {
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.calculateButton.setOnClickListener {
            //throw Exception("Something went wrong!")
            //Crashes.generateTestCrash()

            val interestRate = binding.interestEditText.text.toString().toFloat()
            val currentAge = binding.ageEditText.text.toString().toInt()
            val retirementAge = binding.retirementEditText.text.toString().toInt()

            if (interestRate <= 0) {
                Analytics.trackEvent("wrong_interest_rate")
            }
            if (retirementAge <= currentAge) {
                Analytics.trackEvent("wrong_age")
            }

            binding.resultTextView.text = "At the current rate of $interestRate%..."

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}