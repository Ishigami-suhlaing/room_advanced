package com.example.roomforeinkey.ui.contact

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomforeinkey.R
import com.example.roomforeinkey.data.local.db.AppDatabase
import com.example.roomforeinkey.data.local.entities.ContactEntity
import com.example.roomforeinkey.databinding.ActivityContactBinding
import kotlinx.coroutines.launch

class ContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactBinding
    private lateinit var contactAdapter: ContactAdapter
    private lateinit var viewModel: ContactViewModel


    private var groupId: Int = -1
    private var groupName: String = "Unknown Group"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        groupId = intent.getIntExtra("group_id", -1)
        groupName = intent.getStringExtra("group_name") ?: "Unknown Group"

        if(groupId == -1 ){
            finish()
            return
        }

        val dao = AppDatabase.getInstance(applicationContext).contactDao()
        val factory = ContactViewModelFactory(dao, groupId)
        viewModel = ViewModelProvider(this, factory).get(ContactViewModel::class.java)

        contactAdapter = ContactAdapter(
            emptyList(), groupName){
            contact -> viewModel.deleteContact(contact)
        }


        binding.contactRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ContactActivity)
            adapter = contactAdapter
        }

        lifecycleScope.launch {
            viewModel.getAllContacts(groupId).collect { contactList ->
                contactAdapter.updateContacts(contactList)

            }
        }

        binding.addContactFab.setOnClickListener {
            val contact = ContactEntity(
                name = "Contact ${(0..1000).random()}",
                phoneNumber = "09${(100000000..999999999).random()}",
                groupOwnerId = groupId
            )
            viewModel.upsertContact(contact)
        }



    }
}