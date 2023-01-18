package com.nextgen.kmtest.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.nextgen.kmtest.R
import com.nextgen.kmtest.databinding.FragmentFirstBinding
import com.nextgen.kmtest.helper.showAlertDialog


class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCheck.setOnClickListener {
            checkTextPalindrome()
        }

        binding.btnNext.setOnClickListener {
            val name = binding.etName.text.toString()
            val secondFragment = SecondFragment()
            val bundle = Bundle()
            bundle.putString(SecondFragment.NAME, name)
            secondFragment.arguments = bundle

            parentFragmentManager.commit {
                replace(R.id.container, secondFragment, SecondFragment::class.java.simpleName)
            }
        }

    }

    private fun checkTextPalindrome() {
        binding.etTextPalindrome.text.toString().let {
            if (isPalindrome(it)){
                requireContext().showAlertDialog("isPalindrome")
            }else{
                requireContext().showAlertDialog("not Palindrome")
            }
        }
    }

    private fun isPalindrome(plainText: String): Boolean {
        val text = StringBuilder(plainText).let {
            it.reverse().toString()
        }
        return plainText.equals(text, true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
    }
}