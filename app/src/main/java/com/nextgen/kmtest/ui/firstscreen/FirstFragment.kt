package com.nextgen.kmtest.ui.firstscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.nextgen.kmtest.R
import com.nextgen.kmtest.databinding.FragmentFirstBinding
import com.nextgen.kmtest.helper.showAlertDialog
import com.nextgen.kmtest.ui.secondscreen.SecondFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FirstViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCheck.setOnClickListener {
            checkTextPalindrome()
        }

        binding.btnNext.setOnClickListener {
            val name = binding.etName.text.toString()
            viewModel.saveUser(name)
            parentFragmentManager.commit {
                addToBackStack(FirstFragment::class.java.simpleName)
                replace(R.id.container, SecondFragment(), SecondFragment::class.java.simpleName)
            }
        }

    }

    private fun checkTextPalindrome() {
        binding.etTextPalindrome.text.toString().let {
            if (isPalindrome(it)){
                requireContext().showAlertDialog(getString(R.string.isPalindrome))
            }else{
                requireContext().showAlertDialog(getString(R.string.notPalindrome))
            }
        }
    }

    private fun isPalindrome(plainText: String): Boolean {
        val text = StringBuilder(plainText).reverse().toString()
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