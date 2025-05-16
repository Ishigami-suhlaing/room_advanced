package com.example.roomforeinkey.ui.group

import android.app.Application
import android.content.Intent
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
import com.example.roomforeinkey.data.local.entities.GroupEntity
import com.example.roomforeinkey.databinding.ActivityGroupBinding
import com.example.roomforeinkey.ui.contact.ContactActivity
import kotlinx.coroutines.launch

class GroupActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGroupBinding
    private lateinit var groupAdapter: GroupAdapter
    private lateinit var viewModel: GroupViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        binding = ActivityGroupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        val dao = AppDatabase.getInstance(applicationContext).groupDao()
        val factory = GroupViewModelFactory(dao)
        viewModel = ViewModelProvider(this, factory).get(GroupViewModel::class.java)

        groupAdapter = GroupAdapter(emptyList(),
            onItemClick = {group ->

            val intent = Intent(this, ContactActivity::class.java)
            intent.putExtra("group_id",group.groupId)
            startActivity(intent)
        },
            onDeleteClick = {group ->
                viewModel.deleteGroup(group)
            }
        )

        binding.groupRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@GroupActivity)
            adapter = groupAdapter
        }

        lifecycleScope.launch{
            viewModel.allGroups.collect { groupList ->
                groupAdapter.updateGroups(groupList)
            }
        }

        binding.addGroupFab.setOnClickListener {
            val newGroup = GroupEntity(groupName = "Group ${(0..1000).random()}")
            viewModel.upsertGroup(newGroup)
        }


    }
}