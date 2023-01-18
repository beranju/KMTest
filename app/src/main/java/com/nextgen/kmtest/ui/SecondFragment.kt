package com.nextgen.kmtest.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.commit
import com.nextgen.kmtest.R
import com.nextgen.kmtest.data.remote.response.DataItem
import com.nextgen.kmtest.databinding.FragmentSecondBinding
import com.nextgen.kmtest.ui.thirdscreen.ThirdFragment


class SecondFragment : Fragment() {

    private var _binding : FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        binding.toolbar.title = "Second Screen"

        arguments?.getString(NAME).let {
            binding.tvName.text = it.toString()
        }

        arguments?.getParcelable<DataItem>(SELECTEDUSER).let {
            binding.tvSelectedUser.text = it?.firstName
        }


        binding.btnChooseUser.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.container, ThirdFragment(),ThirdFragment::class.java.simpleName )
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "SecondFragment"
        const val NAME = "name"
        const val SELECTEDUSER = "selectedUser"
    }
}