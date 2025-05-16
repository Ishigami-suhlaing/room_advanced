package com.example.roomforeinkey.ui.group

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.roomforeinkey.R

import androidx.recyclerview.widget.RecyclerView
import com.example.roomforeinkey.data.local.entities.GroupEntity

class GroupAdapter(
    private var groups: List<GroupEntity>,
    private val onItemClick: (GroupEntity) -> Unit
): RecyclerView.Adapter<GroupAdapter.GroupViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GroupViewHolder {
        val  view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_group, parent, false)
        return GroupViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: GroupViewHolder,
        position: Int
    ) {
        holder.bind(groups[position])
    }

    override fun getItemCount(): Int = groups.size

    inner class GroupViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val groupNameText: TextView = itemView.findViewById(R.id.groupNameText)

        fun bind(group: GroupEntity){
            groupNameText.text = group.groupName
            itemView.setOnClickListener {
                onItemClick(group)
            }
        }
    }

    fun updateGroups(newGroups: List<GroupEntity>) {
        groups = newGroups
        notifyDataSetChanged()
    }
}