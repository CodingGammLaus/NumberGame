package se.umu.cs.dv21sln.numbergame

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
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

        /*List*/
        binding.list5Wins.text = sharedPref.getInt("winsList5", 0).toString()
        binding.list10Wins.text = sharedPref.getInt("winsList10", 0).toString()
        binding.list15Wins.text = sharedPref.getInt("winsList15", 0).toString()
        binding.listPlayed.text = sharedPref.getInt("playedList", 0).toString()

        /*Higher*/
        binding.higherStreak.text = sharedPref.getInt("winsHigher", 0).toString()
        binding.higherPlayed.text = sharedPref.getInt("playedHigher", 0).toString()

        /*Guess*/
        binding.guessWins.text = sharedPref.getInt("winsGuess", 0).toString()
        binding.guessPlayed.text = sharedPref.getInt("playedGuess", 0).toString()

        /*Order*/
        binding.orderWins.text = sharedPref.getInt("winsOrder", 0).toString()
        binding.orderPlayed.text = sharedPref.getInt("playedOrder", 0).toString()

        /*Numbers*/
        binding.sixsixsix.text = sharedPref.getInt("666", 0).toString()
        binding.sixnine.text = sharedPref.getInt("69", 0).toString()
        binding.fourtwenty.text = sharedPref.getInt("420", 0).toString()
        binding.elite.text = sharedPref.getInt("1337", 0).toString()
    }

    /**
     * Back init.
     */
    private fun backInit() {

        binding.backButton.setOnClickListener() {

            binding.backButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slump_btn))
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

            shake()
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
    private fun shake() {

        binding.box1.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake))
        binding.box2.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake))
        binding.box3.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake))
        binding.box4.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake))
        binding.box5.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake))
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
        editor.putInt("1337", 0)
        editor.putInt("winsList5", 0)
        editor.putInt("winsList10", 0)
        editor.putInt("winsList15", 0)
        editor.putInt("playedList", 0)
        editor.putInt("winsHigher", 0)
        editor.putInt("playedHigher", 0)
        editor.putInt("winsGuess", 0)
        editor.putInt("playedGuess", 0)
        editor.putInt("winsOrder", 0)
        editor.putInt("playedOrder", 0)
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