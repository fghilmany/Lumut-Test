package com.fghilmany.mvvmstarterproject.ui.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.fghilmany.mvvmstarterproject.core.data.Resource
import com.fghilmany.mvvmstarterproject.databinding.FragmentHomeBinding
import com.fghilmany.mvvmstarterproject.ui.main.MainActivity
import com.fghilmany.mvvmstarterproject.ui.main.MainCallback
import com.fghilmany.mvvmstarterproject.ui.main.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class HomeFragment : Fragment(), ClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModel()

    private lateinit var callback: MainCallback

    companion object{
        val TAG = this::class.simpleName
        fun newInstance(): HomeFragment {
            val args = Bundle()

            val fragment = HomeFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as MainActivity
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getTodos().observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    val listAdapter = HomeAdapter(this)
                    listAdapter.setList(it.data)
                    binding.rvTodos.apply {
                        layoutManager = LinearLayoutManager(requireContext())
                        setHasFixedSize(true)
                        adapter = listAdapter
                    }
                }
                is Resource.Error -> {
                }
            }
        }


    }

    override fun onItemClick(id: String) {
        Timber.e(id)
        viewModel.setId(id)
        viewModel.id.observe(viewLifecycleOwner){
            Timber.e(it)
            callback.setDetail()
        }
    }

}