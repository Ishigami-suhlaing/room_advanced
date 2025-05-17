package com.example.roomforeinkey.ui.contact

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomforeinkey.R
import com.example.roomforeinkey.data.local.entities.ContactEntity

class ContactAdapter(
    private var contacts:List<ContactEntity>,
    private val groupName: String,
    private val onDeleteClick: (ContactEntity) -> Unit
): RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact, parent, false)
        return ContactViewHolder(view)    }

    override fun onBindViewHolder(
        holder: ContactViewHolder,
        position: Int
    ) {
        holder.bind(contacts[position])

    }

    override fun getItemCount(): Int = contacts.size


    inner class ContactViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        private val contactNameText:TextView = itemView.findViewById(R.id.contactNameText)
        private val contactPhoneText: TextView = itemView.findViewById(R.id.contactPhoneText)
        private val contactGroupNameText: TextView = itemView.findViewById(R.id.contactGroupIdText)
        private val contactDeleteBtn: ImageView = itemView.findViewById(R.id.contact_delete_btn)

        fun bind(contact: ContactEntity){
            contactNameText.text = contact.name
            contactPhoneText.text = contact.phoneNumber
            contactGroupNameText.text = groupName
            contactDeleteBtn.setOnClickListener {
                onDeleteClick(contact)
            }


        }

    }

    fun updateContacts(newContacts: List<ContactEntity>) {
        contacts = newContacts
        notifyDataSetChanged()
    }
}