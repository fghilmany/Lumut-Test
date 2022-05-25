package com.fghilmany.mvvmstarterproject.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fghilmany.mvvmstarterproject.core.data.Resource
import com.fghilmany.mvvmstarterproject.databinding.FragmentDetailBinding
import com.fghilmany.mvvmstarterproject.ui.main.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModel()

    companion object{
        val TAG = this::class.simpleName
        fun newInstance(): DetailFragment {
            val args = Bundle()

            val fragment = DetailFragment()
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getDetailTodos().observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    with(binding) {
                        it.data?.apply {
                            tvTitle.text = title.toString()
                            tvCompleted.text = completed.toString()
                        }
                    }
                }
                is Resource.Error -> {
                }
            }

        }

    }


}