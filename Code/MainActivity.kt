package se.umu.cs.dv21sln.numbergame

import android.app.AlertDialog
import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import se.umu.cs.dv21sln.numbergame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*Get the view binding*/
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playInit()
        statsInit()
        settingsInit()
    }

    /**
     * Play init.
     */
    private fun playInit() {

        binding.playButton.setOnClickListener() {

            val intent = Intent(this, PlayActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    /**
     * Stats init.
     */
    private fun statsInit() {

        binding.statsButton.setOnClickListener() {

            val intent = Intent(this, StatsActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    /**
     * Settings init.
     */
    private fun settingsInit() {

        binding.settingsButton.setOnClickListener() {

            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    /**
     *
     */
    override fun onBackPressed() {

        val alert = AlertDialog.Builder(this, R.style.MyDialogTheme)

        alert.setTitle("Quit")
        alert.setMessage("Do you want to quit the game?")
        alert.setPositiveButton("YES") {_, _ ->

            finish()
        }

        alert.setNegativeButton("NO") {_, _ ->

            closeContextMenu()
        }.create()

        alert.show()
    }
}