package com.example.firstappinkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.firstappinkotlin.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter:TodoAdapter
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        todoAdapter = TodoAdapter(mutableListOf())

        binding.todoItems.adapter = todoAdapter
        binding.todoItems.layoutManager = LinearLayoutManager(this)

        binding.buttonAdd.setOnClickListener {
            val todotit = binding.putHere.text.toString()
            if (todotit.isNotEmpty()){
                val todo = Todo(todotit)
                todoAdapter.addTodo(todo)
                binding.putHere.text.clear()
            }
        }

        binding.buttonDelete.setOnClickListener {
            todoAdapter.deleteDDoneTodos()
        }
    }
}