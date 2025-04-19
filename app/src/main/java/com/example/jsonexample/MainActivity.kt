package com.example.jsonexample

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.jsonexample.viewModel.PostViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: PostViewModel
    private lateinit var tvId: TextView
    private lateinit var tvTitle: TextView
    private lateinit var tvBody: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewContainer()

        viewModel = ViewModelProvider(this)[PostViewModel::class.java]

        viewModel.obsResponse.observe(this) { jsonResponse ->
            jsonResponse?.let {
                println("JSON Response: $it")
                tvId.text = viewModel.id.toString()
                tvTitle.text = viewModel.title
                tvBody.text = viewModel.body
            }
        }
        viewModel.fetchData()
    }

    private fun viewContainer() {
        tvId = findViewById(R.id.tvId)
        tvBody = findViewById(R.id.tvBody)
        tvTitle = findViewById(R.id.tvTitle)
    }
}
