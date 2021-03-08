package com.example.bike.riders.feature.main.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bike.riders.R
import com.example.bike.riders.feature.login.view.LoginActivity
import com.example.bike.riders.feature.main.api.Bike
import com.example.bike.riders.feature.main.api.Network
import com.example.bike.riders.feature.main.contracts.MainInteractor
import com.example.bike.riders.feature.main.contracts.MainPresenter
import com.example.bike.riders.feature.main.contracts.MainView
import com.example.bike.riders.feature.main.presenter.MainPresenterImpl
import com.example.bike.riders.feature.maps.view.MapsActivity
import com.example.bike.riders.feature.signup.view.SignupActivity
import com.google.android.material.navigation.NavigationView
import dagger.android.DaggerActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainActivity : DaggerActivity(), MainView , NavigationView.OnNavigationItemSelectedListener{

    private lateinit var presenter: MainPresenter
    @Inject
    lateinit var mainInteractor: MainInteractor
/*
    var title="Notification Title"
    var message="Notification Message"*/

    lateinit var adapter: NetworkAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
       // setSupportActionBar(toolbar)

/*        if (intent.extras != null){
            for (key in intent.extras!!.keySet()){
                if (key == "title"){
                    title=intent.extras!!.getString("title","")
                }
                if (key == "message"){
                    message=intent.extras!!.getString("message","")
                }
            }
        }*/

        progressBar.visibility = View.VISIBLE
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)


        displayScreen(-1)

        presenter = MainPresenterImpl(this,mainInteractor)
        presenter.checkFetchData()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_action -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun displayScreen(id: Int){

        when (id){
            R.id.nav_main -> {

            }

            R.id.nav_map -> {
                startActivity(Intent(this@MainActivity, MapsActivity::class.java))
            }

            R.id.nav_detail -> {
                Toast.makeText(this, "Clicked Details", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_logout -> {
                presenter.checkLoggedOutUser()
            }

            R.id.nav_aboutUs -> {
                Toast.makeText(this, "Clicked About Us", Toast.LENGTH_SHORT).show()
            }

            R.id.nav_privacyPolicy -> {
                Toast.makeText(this, "Clicked Privacy Policy", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        displayScreen(item.itemId)

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun navigateToHomeScreen() {
        startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        finish()   
    }

    override fun displayData(arrayList: ArrayList<Bike>) {
        adapter = NetworkAdapter(this@MainActivity , arrayList)
        bikesList.adapter = adapter
        bikesList.layoutManager = LinearLayoutManager(this@MainActivity)
        progressBar.visibility = View.GONE
    }

    override fun displayError(message: String) {
        progressBar.visibility = View.GONE
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }


}