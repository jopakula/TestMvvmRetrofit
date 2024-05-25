package com.example.testmvvmretrofit.presentation

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testmvvmretrofit.R

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)
        val editText = findViewById<EditText>(R.id.editText)
        val buttonShow = findViewById<Button>(R.id.buttonShow)
        val buttonSave = findViewById<Button>(R.id.buttonSave)
        val buttonFetchUser = findViewById<Button>(R.id.buttonFetchUser)
        val buttonFetchPost = findViewById<Button>(R.id.buttonFetchPost)

        viewModel =
            ViewModelProvider(this, MainViewModelFactory(this)).get(MainViewModel::class.java)
        viewModel.resultLive.observe(
            this,
            { text ->
                textView.text = text
            },
        )
        viewModel.listLive.observe(
            this,
            { post ->
                postAdapter.submitList(post)
            },
        )

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        postAdapter = PostAdapter()
        recyclerView.adapter = postAdapter

        buttonSave.setOnClickListener {
            val text = editText.text.toString()
            viewModel.save(text)
        }

        buttonShow.setOnClickListener {
            viewModel.load()
        }

        buttonFetchUser.setOnClickListener {
            viewModel.fetchUser()
        }

        buttonFetchPost.setOnClickListener {
            viewModel.fetchPost()
        }
    }
}
