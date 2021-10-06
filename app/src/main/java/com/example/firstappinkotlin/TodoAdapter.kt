package com.example.firstappinkotlin

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstappinkotlin.databinding.ItemTodoBinding



class TodoAdapter(

    private val todos:MutableList<Todo>
) :RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){
    
    inner class TodoViewHolder(val binding: ItemTodoBinding):RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            ItemTodoBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }

    fun addTodo(todo:Todo){
        todos.add(todo)
        notifyItemInserted(todos.size -1)
    }

    fun deleteDDoneTodos(){
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(todoTitle:TextView,isChecked:Boolean){
        if (isChecked){
            todoTitle.paintFlags = todoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }else{
            todoTitle.paintFlags = todoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }



    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {

        val curTodo = todos[position]
        holder.itemView.apply {
            holder.binding.todoTitle.text = curTodo.title
            holder.binding.cbDone.isChecked = curTodo.isChecked
            toggleStrikeThrough(holder.binding.todoTitle,curTodo.isChecked)
            holder.binding.cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(holder.binding.todoTitle,isChecked)
                curTodo.isChecked = !curTodo.isChecked
            }
        }

    }

    override fun getItemCount(): Int {
        return todos.size

    }
}