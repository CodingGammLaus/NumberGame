package se.umu.cs.dv21sln.numbergame

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import se.umu.cs.dv21sln.numbergame.databinding.GuessNumberActivityBinding

class GuessNumberActivity : AppCompatActivity() {

    private lateinit var binding: GuessNumberActivityBinding

    var number = 0
    var interval = 100
    var guessNumber = 0
    var round = 1
    var numberWon = 0
    var guesses = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*Get the view binding*/
        binding = GuessNumberActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadInStats()
        backButtonInit()
        guessButtonInit()

        binding.guess.hint = "Guess on a number between 1-" + interval.toString()

        setMaxLength()

        if(guesses == 5) {

            binding.row6.isVisible = false
            binding.row7.isVisible = false
            binding.row8.isVisible = false
            binding.row9.isVisible = false
            binding.row10.isVisible = false
        }
    }

    /**
     *
     */
    private fun loadInStats() {

        val sharedPref = getSharedPreferences("statsList", MODE_PRIVATE)
        numberWon = sharedPref.getInt("winsGuess", 0)
        interval = sharedPref.getInt("guessInterval", 100)
        guesses = sharedPref.getInt("guessGuesses", 5)
    }

    /**
     * Set the length of the input field.
     */
    private fun setMaxLength() {

        if(interval < 10) {

            binding.guess.filters = arrayOf(InputFilter.LengthFilter(1))
        }

        else if(interval > 9 && interval < 100) {

            binding.guess.filters = arrayOf(InputFilter.LengthFilter(2))
        }

        else if(interval > 99 && interval < 1000) {

            binding.guess.filters = arrayOf(InputFilter.LengthFilter(3))
        }

        else if(interval > 999 && interval < 10000) {

            binding.guess.filters = arrayOf(InputFilter.LengthFilter(4))
        }

        else {

            binding.guess.filters = arrayOf(InputFilter.LengthFilter(10))
        }
    }

    /**
     * Back init.
     */
    private fun backButtonInit() {

        binding.backButton.setOnClickListener() {

            popupQuit()
        }
    }

    /**
     *
     */
    private fun guessButtonInit() {

        binding.playButton.setOnClickListener() {

            number = (1..interval).random()
            binding.playButton.text = "GUESS " + number.toString()
            binding.guess.isVisible = true

            guessingInit()
        }
    }

    /**
     *
     */
    private fun guessingInit() {

        binding.playButton.setOnClickListener() {

            if(binding.guess.text.isNotEmpty()) {

                makeGuess()
                checkGuess()
                round++
                binding.guess.text.clear()
            }
        }
    }

    /**
     *
     */
    private fun makeGuess() {

        guessNumber = binding.guess.text.toString().toInt()

        if(round == 1) {

            binding.guess1.text = guessNumber.toString()

            if(number < guessNumber) {

                binding.image1.rotation = 180f
            }

            binding.image1.isVisible = true
        }

        else if(round == 2) {

            binding.guess2.text = guessNumber.toString()

            if(number < guessNumber) {

                binding.image2.rotation = 180f
            }

            binding.image2.isVisible = true
        }

        else if(round == 3) {

            binding.guess3.text = guessNumber.toString()

            if(number < guessNumber) {

                binding.image3.rotation = 180f
            }

            binding.image3.isVisible = true
        }

        else if(round == 4) {

            binding.guess4.text = guessNumber.toString()

            if(number < guessNumber) {

                binding.image4.rotation = 180f
            }

            binding.image4.isVisible = true
        }

        else if(round == 5) {

            binding.guess5.text = guessNumber.toString()

            if(number < guessNumber) {

                binding.image5.rotation = 180f
            }

            binding.image5.isVisible = true
        }

        else if(round == 6) {

            binding.guess6.text = guessNumber.toString()

            if(number < guessNumber) {

                binding.image6.rotation = 180f
            }

            binding.image6.isVisible = true
        }

        else if(round == 7) {

            binding.guess7.text = guessNumber.toString()

            if(number < guessNumber) {

                binding.image7.rotation = 180f
            }

            binding.image7.isVisible = true
        }

        else if(round == 8) {

            binding.guess8.text = guessNumber.toString()

            if(number < guessNumber) {

                binding.image8.rotation = 180f
            }

            binding.image8.isVisible = true
        }

        else if(round == 9) {

            binding.guess9.text = guessNumber.toString()

            if(number < guessNumber) {

                binding.image9.rotation = 180f
            }

            binding.image9.isVisible = true
        }

        else if(round == 10) {

            binding.guess10.text = guessNumber.toString()

            if(number < guessNumber) {

                binding.image10.rotation = 180f
            }

            binding.image10.isVisible = true
        }
    }

    /**
     *
     */
    private fun checkGuess() {

        /*Win*/
        if(guessNumber == number) {

            win()
            binding.guess.isEnabled = false
        }

        /*Lose*/
        else if(guessNumber != number && round == guesses) {

            lose()
            binding.guess.isEnabled = false
        }

        /*To high*/
        else if(guessNumber > number) {

            binding.guessText.text = "Lower"
        }

        /*To low*/
        else if (guessNumber < number) {

            binding.guessText.text = "Higher"
        }
    }

    /**
     *
     */
    private fun win() {

        binding.guessText.text = "Win!"
        numberWon++
        saveStats()

        popUpDone("You won", "You guessed the right number!")

        binding.playButton.text = "Popup"
        binding.playButton.setOnClickListener() {

            popUpDone("You won", "You guessed the right number!")
        }
    }

    /**
     *
     */
    private fun lose() {

        binding.guessText.text = "Lose!"

        popUpDone("You lose", "You didn't guess the right number!")

        binding.playButton.text = "Popup"
        binding.playButton.setOnClickListener() {

            popUpDone("You lose", "You didn't guess the right number!")
        }
    }

    /**
     *
     */
    private fun saveStats() {

        val sharedPref = getSharedPreferences("statsList", MODE_PRIVATE)
        val editor = sharedPref.edit()

        editor.putInt("winsGuess", numberWon)
        editor.apply()
    }

    /**
     *
     */
    private fun popUpDone(title: String, message: String) {

        val alert = AlertDialog.Builder(this, R.style.MyDialogTheme)

        alert.setTitle(title)
        alert.setMessage(message + "\nThe correct number was: " + number)
        alert.setPositiveButton("Play again") {_, _ ->

            val intent = Intent(this, GuessNumberActivity::class.java)
            startActivity(intent)
            finish()
        }

        alert.setNegativeButton("Exit") {_, _ ->

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }.create()

        alert.show()
    }

    /**
     *
     */
    private fun popupQuit() {

        val alert = AlertDialog.Builder(this, R.style.MyDialogTheme)

        alert.setTitle("Quit")
        alert.setMessage("Do you want to quit?")
        alert.setPositiveButton("YES") {_, _ ->

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        alert.setNegativeButton("NO") {_, _ ->

            closeContextMenu()
        }.create()

        alert.show()
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