package com.example.sampleapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.sampleapp.R
import com.example.sampleapp.databinding.ActivityMainBinding
import com.example.sampleapp.util.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels()
    @Inject lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvEmployees.adapter = userAdapter

        mainViewModel.result.observe(this) {

            when (it.status) {
                Status.SUCCESS -> {

                    binding.progressBar.visibility = View.GONE
                    binding.rvEmployees.visibility = View.VISIBLE

                    it.data.let { res ->
                        res?.data?.let { data ->
                            userAdapter.submitList(data)
                        }
                    }
                }

                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.rvEmployees.visibility = View.GONE
                }

                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    binding.rvEmployees.visibility = View.GONE
                    Toast.makeText(
                        this,
                        getString(R.string.something_went_wrong),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }
    }
}