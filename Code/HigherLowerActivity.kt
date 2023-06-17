package se.umu.cs.dv21sln.numbergame

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import se.umu.cs.dv21sln.numbergame.databinding.HigherLowerActivityBinding


class HigherLowerActivity : AppCompatActivity() {

    private lateinit var binding: HigherLowerActivityBinding

    private var interval = 1000
    private var currentNumber = 0
    private var newNumber = 0
    private var points = 0
    private var highestPoints = 0
    private var played = 0
    private var lives = 0

    private var sixnine = 0
    private var sixsixsix = 0
    private var fourtwenty = 0
    private var elite = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*Get the view binding*/
        binding = HigherLowerActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadInStats()
        lives()
        startGame()
        quitInit()
    }

    /**
     *
     */
    private fun loadInStats() {

        val sharedPref = getSharedPreferences("statsList", MODE_PRIVATE)
        highestPoints = sharedPref.getInt("winsHigher", 0)
        played = sharedPref.getInt("playedHigher", 0)
        sixnine = sharedPref.getInt("69", 0)
        sixsixsix = sharedPref.getInt("666", 0)
        fourtwenty = sharedPref.getInt("420", 0)
        elite = sharedPref.getInt("1337", 0)
        interval = sharedPref.getInt("higherInterval", 1000)
        lives = sharedPref.getInt("lives", 0)
    }

    /**
     *
     */
    private fun lives() {

        if(lives == 0) {
            binding.lives1.isVisible = false
            binding.lives2.isVisible = false
            binding.lives3.isVisible = false
        }

        else if(lives == 1) {

            binding.lives2.isVisible = false
            binding.lives3.isVisible = false
        }

        else if(lives == 2) {

            binding.lives3.isVisible = false
        }
    }

    /**
     *
     */
    private fun startGame() {

        binding.higherButton.setOnClickListener() {

            binding.higherButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slump_btn))

            played++

            newNumber = (0..interval).random()
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

            binding.higherButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slump_btn))

            slump()

            if(newNumber < currentNumber) {

                checkLives()
            }

            else {

                points++
                binding.pointText.text = "Points " + points

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

            binding.lowerButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slump_btn))

            slump()

            if(newNumber > currentNumber) {

                checkLives()
            }

            else {
                points++
                binding.pointText.text = "Points " + points

                pointAnimation()
            }

            currentNumber = newNumber
        }
    }

    /**
     *
     */
    private fun checkLives() {

        if(lives <= 0) {
            lose()
        }

        else if(lives == 1) {

            Handler().postDelayed(Runnable {
                binding.lives1.setImageResource(R.drawable.baseline_favorite_border_48)
            }, 300)

            binding.lives1.startAnimation(AnimationUtils.loadAnimation(this, R.anim.lives_loss))
        }

        else if(lives == 2) {

            Handler().postDelayed(Runnable {
                binding.lives2.setImageResource(R.drawable.baseline_favorite_border_48)
            }, 300)

            binding.lives2.startAnimation(AnimationUtils.loadAnimation(this, R.anim.lives_loss))
        }

        else if(lives == 3) {

            Handler().postDelayed(Runnable {
                binding.lives3.setImageResource(R.drawable.baseline_favorite_border_48)
            }, 300)

            binding.lives3.startAnimation(AnimationUtils.loadAnimation(this, R.anim.lives_loss))
        }

        lives--
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

        newNumber = (0..interval).random()
        checkSlump()

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

        else if(newNumber == 1337) {
            elite++
        }
    }

    /**
     *
     */
    private fun checkSlump() {

        if(newNumber == currentNumber) {

            newNumber = (0..interval).random()
            checkSlump()
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

        editor.putInt("playedHigher", played)
        editor.putInt("69", sixnine)
        editor.putInt("666", sixsixsix)
        editor.putInt("420", fourtwenty)
        editor.putInt("1337", elite)
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

            binding.quitButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slump_btn))
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

        saveStats()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    /**
     *
     */
    override fun onBackPressed() {
        super.onBackPressed()
        saveStats()
        val intent = Intent(this, PlayActivity::class.java)
        startActivity(intent)
        finish()
    }
}