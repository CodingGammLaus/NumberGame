package se.umu.cs.dv21sln.numbergame

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import se.umu.cs.dv21sln.numbergame.databinding.StatsActivityBinding

class StatsActivity : AppCompatActivity() {

    private lateinit var binding: StatsActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*Get the view binding*/
        binding = StatsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadInStat()

        backInit()

        resetInit()
    }

    /**
     * Load in the stats.
     */
    private fun loadInStat() {

        val sharedPref = getSharedPreferences("statsList", MODE_PRIVATE)

        binding.sixsixsixText.text = sharedPref.getInt("666", 0).toString()
        binding.sixnineText.text = sharedPref.getInt("69", 0).toString()
        binding.fourtwentyText.text = sharedPref.getInt("420", 0).toString()
        binding.winsListText.text = sharedPref.getInt("winsList", 0).toString()
        binding.winsHigherText.text = sharedPref.getInt("winsHigher", 0).toString()
        binding.winsGuessText.text = sharedPref.getInt("winsGuess", 0).toString()
    }

    /**
     * Back init.
     */
    private fun backInit() {

        binding.backButton.setOnClickListener() {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    /**
     * Reset button init.
     */
    private fun resetInit() {

        binding.resetButton.setOnClickListener() {

            askIfReset()
        }
    }

    /**
     *
     */
    private fun askIfReset() {

        val alert = AlertDialog.Builder(this, R.style.MyDialogTheme)

        alert.setTitle("Reset stats")
        alert.setMessage("Do you want to reset the stats?")
        alert.setPositiveButton("YES") {_, _ ->

            resetStats()
        }

        alert.setNegativeButton("NO") {_, _ ->

            closeContextMenu()
        }.create()

        alert.show()
    }

    /**
     *
     */
    private fun resetStats() {

        val sharedPref = getSharedPreferences("statsList", MODE_PRIVATE)
        val editor = sharedPref.edit()

        editor.putInt("69", 0)
        editor.putInt("666", 0)
        editor.putInt("420", 0)
        editor.putInt("winsList", 0)
        editor.putInt("winsHigher", 0)
        editor.putInt("winsGuess", 0)
        editor.apply()
        loadInStat()
    }

    /**
     *
     */
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}