package com.example.themoviedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.themoviedb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding ?: throw NullPointerException("View wasn't created")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}