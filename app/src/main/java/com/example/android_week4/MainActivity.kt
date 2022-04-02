package com.example.android_week4

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.widget.`LinearLayoutCompat$InspectionCompanion`
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_week4.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity(), RestaurantAdapter.OnItemClickListener {
    lateinit var binding : ActivityMainBinding
    lateinit var  viewModel: MainVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setSupportActionBar(binding.myToolbar)
        viewModel = ViewModelProvider(this).get(MainVM::class.java)

        val data = RestaurantStore.getDataSet()
        val adapter = RestaurantAdapter(data,this)
        val lm = LinearLayoutManager(this)
        binding.dsrestaurant.layoutManager = lm
        binding.dsrestaurant.adapter = adapter

    }
    private fun showDialog() {
       var builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Option")
        builder.setMessage("Delete it now Are you sure ?")
        builder.setPositiveButton("Yes",DialogInterface.OnClickListener { dialogInterface, i ->

        } )
        builder.setNegativeButton("No",DialogInterface.OnClickListener { dialogInterface, i ->
            dialogInterface.cancel()
        })
        builder.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu,menu)
        return true
    }

    override fun onItemClick(position: Int) {
        showDialog()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.item1 ->{
                val data = RestaurantStore.getDataSet()
                val adapter = RestaurantAdapter(data,this)
                val lm = LinearLayoutManager(this)
                binding.dsrestaurant.layoutManager = lm
                binding.dsrestaurant.adapter = adapter
                Toast.makeText(this,"Linear Layout Manager",Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.item2 ->{
                val data = RestaurantStore.getDataSet()
                val adapter = RestaurantAdapter(data,this)
                val grid = GridLayoutManager(this,2,LinearLayoutManager.VERTICAL,false)
                binding.dsrestaurant.layoutManager = grid
                binding.dsrestaurant.adapter = adapter
                binding.dsrestaurant.setHasFixedSize(true)
                Toast.makeText(this,"Grid Layout Manager",Toast.LENGTH_SHORT).show()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}