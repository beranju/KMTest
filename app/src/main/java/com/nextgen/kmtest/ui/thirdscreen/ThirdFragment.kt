package com.nextgen.kmtest.ui.thirdscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.paging.LoadState
import androidx.paging.filter
import androidx.recyclerview.widget.LinearLayoutManager
import com.nextgen.kmtest.R
import com.nextgen.kmtest.databinding.FragmentThirdBinding
import com.nextgen.kmtest.ui.secondscreen.SecondFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class ThirdFragment : Fragment() {

    private var _binding : FragmentThirdBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ThirdViewModel by viewModel()
    private lateinit var userAdapter: UserAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()

        userAdapter = UserAdapter()
        setupRecyclerView()

        userAdapter.onClick = {selectedItem->
            val bundle = Bundle()
            bundle.putParcelable(SecondFragment.SELECTEDUSER, selectedItem)
            val secondFragment = SecondFragment()
            secondFragment.arguments = bundle
            parentFragmentManager.commit {
                addToBackStack(null)
                replace(R.id.container, secondFragment, SecondFragment::class.java.simpleName)
            }
        }

        viewModel.getUser.observe(viewLifecycleOwner){result->
            if (result != null){
                userAdapter.submitData(lifecycle, result)
                binding.noData.visibility = View.GONE
            }else{
                binding.noData.visibility = View.VISIBLE
            }
        }

    }

    private fun setupRecyclerView() {
        binding.rvItemUser.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = userAdapter.withLoadStateFooter(
                footer = LoadingStateAdapter{ userAdapter.retry() }
            )
            userAdapter.addLoadStateListener {
                if (it.refresh == LoadState.Loading){
                    binding.pbThirdscreen.visibility = View.VISIBLE
                }else{
                    binding.pbThirdscreen.visibility = View.GONE
                }
            }
        }
    }

    private fun setupToolbar() {
        binding.toolbar.apply {
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener {
                parentFragmentManager.popBackStack()
            }
            title = "Third Screen"
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