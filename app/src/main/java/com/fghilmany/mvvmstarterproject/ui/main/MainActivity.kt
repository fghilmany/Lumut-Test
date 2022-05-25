package com.fghilmany.mvvmstarterproject.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.fghilmany.mvvmstarterproject.R
import com.fghilmany.mvvmstarterproject.core.data.Resource
import com.fghilmany.mvvmstarterproject.databinding.ActivityMainBinding
import com.fghilmany.mvvmstarterproject.ui.detail.DetailFragment
import com.fghilmany.mvvmstarterproject.ui.home.HomeFragment
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity(), MainCallback {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModel()

    private var isLarge = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (binding.frameList != null){
            isLarge = true
        }

        populateFragment()


    }

    private fun populateFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if (!isLarge) {
            var fragment = supportFragmentManager.findFragmentByTag(HomeFragment.TAG)
            if (fragment == null) {
                fragment = HomeFragment.newInstance()
                fragmentTransaction.add(R.id.frame_container, fragment, HomeFragment.TAG)
                fragmentTransaction.addToBackStack(null)
            }
            fragmentTransaction.commit()
        } else {

            var fragmentList = supportFragmentManager.findFragmentByTag(HomeFragment.TAG)

            if (fragmentList == null) {
                fragmentList = HomeFragment.newInstance()
                fragmentTransaction.add(R.id.frame_list, fragmentList, HomeFragment.TAG)
            }

            var fragmentContent = supportFragmentManager.findFragmentByTag(DetailFragment.TAG)
            if (fragmentContent == null) {
                fragmentContent = DetailFragment.newInstance()
                fragmentTransaction.add(R.id.frame_content, fragmentContent, DetailFragment.TAG)
            }
            fragmentTransaction.commit()
        }
    }

    override fun setDetail() {
        if (!isLarge) {
            val fragment = DetailFragment.newInstance()
            supportFragmentManager.beginTransaction().add(R.id.frame_container, fragment, DetailFragment.TAG)
                .addToBackStack(null)
                .commit()
        }
    }

}