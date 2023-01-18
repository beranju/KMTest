package com.nextgen.kmtest.ui.secondscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.nextgen.kmtest.R
import com.nextgen.kmtest.data.remote.response.DataItem
import com.nextgen.kmtest.databinding.FragmentSecondBinding
import com.nextgen.kmtest.ui.thirdscreen.ThirdFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class SecondFragment : Fragment() {

    private var _binding : FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SecondViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()

        binding.tvName.text = viewModel.user

        arguments?.getParcelable<DataItem>(SELECTEDUSER).let {
            binding.tvSelectedUser.text = it?.firstName
        }

        binding.btnChooseUser.setOnClickListener {
            parentFragmentManager.commit {
                addToBackStack(null)
                replace(R.id.container, ThirdFragment(),ThirdFragment::class.java.simpleName )
            }
        }
    }

    private fun setupToolbar() {
        binding.toolbar.apply {
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener {
                parentFragmentManager.popBackStack()
            }
            title = context.getString(R.string.second_fragment_title)
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
        const val SELECTEDUSER = "selectedUser"
    }
}