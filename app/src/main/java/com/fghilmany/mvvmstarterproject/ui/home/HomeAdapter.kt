package com.fghilmany.mvvmstarterproject.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fghilmany.mvvmstarterproject.R
import com.fghilmany.mvvmstarterproject.core.data.local.entity.TodosEntity
import com.fghilmany.mvvmstarterproject.databinding.ItemListBinding

class HomeAdapter internal constructor(private val listener: ClickListener): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private val list = arrayListOf<TodosEntity>()
    fun setList(list: List<TodosEntity>?){
        if (list != null) {
            this.list.clear()
            this.list.addAll(list)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = list[position]
        holder.bind(result)
        holder.itemView.setOnClickListener {
            listener.onItemClick(result.id.toString())
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = ItemListBinding.bind(view)
        fun bind(result: TodosEntity) {
            with(binding){
                result.apply {
                    tvCompleted.text = completed.toString()
                    tvTitle.text = title.toString()
                }
            }
        }

    }
}

interface ClickListener {
    fun onItemClick(id: String)
}
