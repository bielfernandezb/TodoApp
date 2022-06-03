package com.bielfernandezb.todoapp.main.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bielfernandezb.todoapp.databinding.TaskItemBinding
import com.bielfernandezb.todoapp.model.entities.Task

class TasksAdapter(private val listener: TaskItemListener) :
    RecyclerView.Adapter<TasksViewHolder>() {

    private lateinit var binding: TaskItemBinding

    interface TaskItemListener {
        fun onClickedTask(task: Task)
    }

    private val items = ArrayList<Task>()

    fun setItems(items: ArrayList<Task>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        binding =
            TaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TasksViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}

class TasksViewHolder(
    private val itemBinding: TaskItemBinding,
    private val listener: TasksAdapter.TaskItemListener
) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var task: Task

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: Task) {
        this.task = item
        itemBinding.title.text = item.mTitle
        itemBinding.complete.isChecked = item.mCompleted
    }


    override fun onClick(v: View?) {
        listener.onClickedTask(task)
    }
}