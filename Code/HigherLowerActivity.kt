package se.umu.cs.dv21sln.numbergame

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import se.umu.cs.dv21sln.numbergame.databinding.HigherLowerActivityBinding


class HigherLowerActivity : AppCompatActivity() {

    private lateinit var binding: HigherLowerActivityBinding

    private var interval = 1000
    private var currentNumber = 0
    private var newNumber = 0
    private var points = 0
    private var highestPoints = 0

    private var sixnine = 0
    private var sixsixsix = 0
    private var fourtwenty = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*Get the view binding*/
        binding = HigherLowerActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadInStats()
        startGame()
        quitInit()
    }

    /**
     *
     */
    private fun loadInStats() {

        val sharedPref = getSharedPreferences("statsList", MODE_PRIVATE)
        highestPoints = sharedPref.getInt("winsHigher", 0)
        sixnine = sharedPref.getInt("69", 0)
        sixsixsix = sharedPref.getInt("666", 0)
        fourtwenty = sharedPref.getInt("420", 0)
        interval = sharedPref.getInt("higherInterval", 1000)
    }

    /**
     *
     */
    private fun startGame() {

        binding.higherButton.setOnClickListener() {

            newNumber = (1..interval).random()
            binding.numberText.text = "Number: " + newNumber.toString()
            slumpAnimation()
            currentNumber = newNumber

            higherInit()
            lowerInit()
        }
    }

    /**
     * Higher init.
     */
    private fun higherInit() {

        binding.higherButton.text = "HIGHER"

        binding.higherButton.setOnClickListener() {

            slump()

            if(newNumber < currentNumber) {

                lose()
            }

            else {

                points++
                binding.pointText.text = "Points: " + points

                pointAnimation()
            }

            currentNumber = newNumber
        }
    }

    /**
     * Lower init.
     */
    private fun lowerInit() {

        binding.lowerButton.isEnabled = true

        binding.lowerButton.setOnClickListener() {

            slump()

            if(newNumber > currentNumber) {

                lose()
            }

            else {
                points++
                binding.pointText.text = "Points: " + points

                pointAnimation()
            }

            currentNumber = newNumber
        }
    }

    /**
     *
     */
    private fun pointAnimation() {

        binding.pointText.startAnimation(AnimationUtils.loadAnimation(this, R.anim.bounce))

        ObjectAnimator.ofFloat(binding.circle, View.ROTATION, 0f, 3600f).apply {
            duration = 1500
            start()
        }
    }

    /**
     *
     */
    private fun slumpAnimation() {

        binding.numberText.startAnimation(AnimationUtils.loadAnimation(this, R.anim.popout))
    }

    /**
     *
     */
    private fun slump() {

        newNumber = (1..interval).random()
        binding.numberText.text = "Number: " + newNumber.toString()
        slumpAnimation()

        if(newNumber == 69) {

            sixnine++
        }

        else if(newNumber == 420) {

            fourtwenty++
        }

        else if(newNumber == 666) {

            sixsixsix++
        }
    }

    /**
     * Lose.
     */
    private fun lose() {

        binding.lowerButton.isEnabled = false

        if(points > highestPoints) {

            saveHighScore()
        }

        saveStats()
        askToPlayAgain()
        playAgainInit()
    }

    /**
     *
     */
    private fun saveStats() {

        val sharedPref = getSharedPreferences("statsList", MODE_PRIVATE)
        val editor = sharedPref.edit()

        editor.putInt("69", sixnine)
        editor.putInt("666", sixsixsix)
        editor.putInt("420", fourtwenty)
        editor.apply()
    }

    /**
     *
     */
    private fun saveHighScore() {

        val sharedPref = getSharedPreferences("statsList", MODE_PRIVATE)
        val editor = sharedPref.edit()

        editor.putInt("winsHigher", points)
        editor.apply()
    }

    /**
     *
     */
    private fun playAgainInit() {

        binding.higherButton.text = "RETRY"

        binding.higherButton.setOnClickListener() {

            val intent = Intent(this, HigherLowerActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    /**
     *
     */
    private fun askToPlayAgain() {

        val alert = android.app.AlertDialog.Builder(this, R.style.MyDialogTheme)

        alert.setTitle("LOSER")
        alert.setMessage("You loss, you got " + points + " points")
        alert.setPositiveButton("Play Again") { dialog, _ ->

            dialog.cancel()

            val intent = Intent(this, HigherLowerActivity::class.java)
            startActivity(intent)
            finish()
        }

        alert.setNegativeButton("Exit") {_, _ ->

            quit()
        }.create()

        alert.show()
    }

    /**
     * Quit init.
     */
    private fun quitInit() {

        binding.quitButton.setOnClickListener() {

            popupQuit()
        }
    }

    /**
     * Ask if quit.
     */
    private fun popupQuit() {

        val alert = AlertDialog.Builder(this, R.style.MyDialogTheme)

        alert.setTitle("Quit")
        alert.setMessage("Do you want to quit?")
        alert.setPositiveButton("YES") {_, _ ->

            quit()
        }

        alert.setNegativeButton("NO") {_, _ ->

            closeContextMenu()
        }.create()

        alert.show()
    }

    /**
     *
     */
    private fun quit() {

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    /**
     *
     */
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, PlayActivity::class.java)
        startActivity(intent)
        finish()
    }
}