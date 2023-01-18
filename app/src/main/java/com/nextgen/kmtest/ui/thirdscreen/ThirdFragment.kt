package com.nextgen.kmtest.ui.thirdscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nextgen.kmtest.R
import com.nextgen.kmtest.databinding.FragmentThirdBinding
import com.nextgen.kmtest.ui.SecondFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class ThirdFragment : Fragment() {

    private var _binding : FragmentThirdBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ThirdViewModel by viewModel()
    private lateinit var userAdapter: UserAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userAdapter = UserAdapter()
        binding.rvItemUser.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = userAdapter
        }

        userAdapter.onClick = {selectedItem->
            val bundle = Bundle()
            bundle.putParcelable(SecondFragment.SELECTEDUSER, selectedItem)
            val secondFragment = SecondFragment()
            secondFragment.arguments = bundle
            parentFragmentManager.commit {
                replace(R.id.container, secondFragment, SecondFragment::class.java.simpleName)
            }
        }

        viewModel.getUser.observe(viewLifecycleOwner){result->
            userAdapter.submitData(lifecycle, result)
        }



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "ThirdFragment"
    }
}