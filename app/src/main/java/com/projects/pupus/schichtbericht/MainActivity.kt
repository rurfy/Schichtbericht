package com.projects.pupus.schichtbericht

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.widget.Button
import android.widget.TextView
import com.projects.pupus.schichtbericht.DatenTypen.Moment
import kotlinx.android.synthetic.main.content_main.*
import java.time.LocalDateTime
import android.support.v7.app.AlertDialog
import android.text.InputType
import android.util.Log
import android.widget.EditText


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val patternDay = "dd.MM.yyyy"
    val patternTime = "HH:mm"

    var schichtStartTag: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        bSchichtBeginn.setOnClickListener {
            var schichtBeginn = Moment(LocalDateTime.now(), getOrtFromAlert())
            setAllTexts(bSchichtBeginn, tvSchichtBeginnTag, tvSchichtBeginnOrt, schichtBeginn)
            setButtonPressedColor(bSchichtBeginn)
        }

        bSchichtEnd.setOnClickListener {
            var schichtEnde = Moment(LocalDateTime.now(), getOrtFromAlert())
            setAllTexts(bSchichtEnd, tvSchichtEndeTag, tvSchichtEndeOrt, schichtEnde)
            setButtonPressedColor(bSchichtEnd)
        }

        bFahrtBeginn.setOnClickListener {
            var fahrtBeginn = Moment(LocalDateTime.now(), getOrtFromAlert())
            setAllTexts(bFahrtBeginn, tvFahrtBeginnTag, tvFahrtBeginnOrt, fahrtBeginn)
            setButtonPressedColor(bFahrtBeginn)
        }

        bFahrtEnd.setOnClickListener {
            var fahrtEnde = Moment(LocalDateTime.now(), getOrtFromAlert())
            setAllTexts(bFahrtEnd, tvFahrtEndeTag, tvFahrtEndeOrt, fahrtEnde)
            setButtonPressedColor(bFahrtEnd)
        }
    }

    fun setButtonPressedColor(button: Button) {
        button.setTextColor(getColor(R.color.colorPrimary))
        if (button.contentDescription == "right")
            button.setBackgroundResource(R.drawable.roundedbutton_right_white)
        else
            button.setBackgroundResource(R.drawable.roundedbutton_left_white)
    }

    fun setAllTexts(button: Button, datum: TextView, ort: TextView, moment: Moment) {
        button.text = "${moment.getTime()} Uhr"
        datum.text = moment.getDay()
        ort.text = moment.ort
    }

    fun getOrtFromAlert(): String {
        var ort = ""

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Geben Sie einen Ort an")

        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

        builder.setPositiveButton("OK") { _, _ -> ort = input.text.toString() }
        builder.setNegativeButton("Abbrechen") { dialog, _ -> dialog.cancel() }

        builder.show()

        return ort
    }


    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_tools -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
