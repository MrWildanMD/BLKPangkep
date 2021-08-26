package com.blk.blkpangkep

import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.blk.blkpangkep.databinding.ActivityMainBinding
import org.apache.commons.net.ftp.FTP
import org.apache.commons.net.ftp.FTPClient
import java.io.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private val LOG_TAG : String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.main_fragment)
//        setupActionBarWithNavController(this, navController)
        setupSmoothBottomMenu()
    }


//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.another_menu, menu)
//        return true
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.another_item_1 -> {
//                showToast("Another Menu Item 1 Selected")
//            }
//
//            R.id.another_item_2 -> {
//                showToast("Another Menu Item 2 Selected")
//            }
//
//            R.id.another_item_3 -> {
//                showToast("Another Menu Item 3 Selected")
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }


//    private fun showToast(msg: String) {
//        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
//    }

    private fun setupSmoothBottomMenu() {
        val popupMenu = PopupMenu(this, null)
        popupMenu.inflate(R.menu.menu_nav)
        val menu = popupMenu.menu
        binding.bottomBar.setupWithNavController(menu, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    companion object {
        init {
            System.loadLibrary("core")
        }
    }
}