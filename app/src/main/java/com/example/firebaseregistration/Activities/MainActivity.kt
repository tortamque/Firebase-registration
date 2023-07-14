package com.example.firebaseregistration.Activities

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseregistration.Adapters.UsersAdapter
import com.example.firebaseregistration.Models.Users
import com.example.firebaseregistration.R
import com.example.firebaseregistration.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    val database = FirebaseDatabase.getInstance()
    val dbReference = database.getReference().child("Users")

    val users = ArrayList<Users>()
    lateinit var usersAdapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, AddUserActivity::class.java)
            startActivity(intent)
        }

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val id = usersAdapter.getUserId(viewHolder.adapterPosition)

                dbReference.child(id).removeValue()

                Toast.makeText(applicationContext, "The user was deleted", Toast.LENGTH_SHORT).show()
            }

        }).attachToRecyclerView(binding.recyclerView)

        retrieveData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_delete_all, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.deleteAll){
            showDialog()
        }

        return super.onOptionsItemSelected(item)
    }

    fun retrieveData(){
        dbReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                users.clear()

                for (dbUser in snapshot.children){
                    val user = dbUser.getValue(Users::class.java)

                    if(user != null){
                        users.add(user)
                    }

                    usersAdapter = UsersAdapter(this@MainActivity, users)

                    binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    binding.recyclerView.adapter = usersAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    fun showDialog(){
        val dialog = AlertDialog.Builder(this)
            .setTitle("Delete All Users")
            .setMessage("Do you really want to delete all users from the database?")
            .setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.cancel()
            })
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
                dbReference.removeValue().addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        usersAdapter.notifyDataSetChanged()

                        Toast.makeText(applicationContext, "All users were deleted", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        dialog.create().show()
    }
}