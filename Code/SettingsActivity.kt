package se.umu.cs.dv21sln.numbergame

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import se.umu.cs.dv21sln.numbergame.databinding.SettingsActivityBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: SettingsActivityBinding

    private var listInterval = 1000
    private var higherInterval = 1000
    private var guessInterval = 100
    private var guessGuesses = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*Get the view binding*/
        binding = SettingsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadInStats()
        backButtonInit()
        changeListIntervalInit()
        changeHigherIntervalInit()
        changeGuessIntervalInit()
        setGuessesInit()
    }

    /**
     * Load in the stats.
     */
    private fun loadInStats() {

        val sharedPref = getSharedPreferences("statsList", MODE_PRIVATE)

        listInterval = sharedPref.getInt("listInterval", 1000)
        higherInterval = sharedPref.getInt("higherInterval", 1000)
        guessInterval = sharedPref.getInt("guessInterval", 100)
        guessGuesses = sharedPref.getInt("guessGuesses", 5)

        binding.listIntervallText.text = "1-" + listInterval.toString()
        binding.higherIntervallText.text = "1-" + higherInterval.toString()
        binding.guessIntervallText.text = "1-" + guessInterval.toString()
        binding.guessGameText.text = "Guess: " + guessGuesses.toString()

        if(guessGuesses == 10) {
            binding.changeGuessGame.text = "SET 5"
        }

        else {
            binding.changeGuessGame.text = "SET 10"
        }
    }

    /**
     * Back button init.
     */
    private fun backButtonInit() {

        binding.backButton.setOnClickListener() {

            saveStats()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    /**
     *
     */
    private fun changeListIntervalInit() {

        binding.changeListIntervall.setOnClickListener() {

            val input = EditText(this)
            input.inputType = InputType.TYPE_CLASS_NUMBER

            val alert = AlertDialog.Builder(this, R.style.MyDialogTheme)

            alert.setTitle("Change interval")
            alert.setView(input)
            alert.setMessage("Change the interval value")
            alert.setPositiveButton("Done") { dialog, _ ->

                if(input.text.toString() != "") {
                    listInterval = input.text.toString().toInt()

                    if(listInterval < 1) {

                        listInterval = 2
                    }
                }

                binding.listIntervallText.text = "1-" + listInterval.toString()
            }
            alert.show()
        }
    }

    /**
     *
     */
    private fun changeHigherIntervalInit() {

        binding.changeHigherIntervall.setOnClickListener() {

            val input = EditText(this)
            input.inputType = InputType.TYPE_CLASS_NUMBER

            val alert = AlertDialog.Builder(this, R.style.MyDialogTheme)

            alert.setTitle("Change interval")
            alert.setView(input)
            alert.setMessage("Change the interval value")
            alert.setPositiveButton("Done") { dialog, _ ->

                if(input.text.toString() != "") {
                    higherInterval = input.text.toString().toInt()

                    if(higherInterval < 1) {

                        higherInterval = 2
                    }
                }

                binding.higherIntervallText.text = "1-" + higherInterval.toString()
            }
            alert.show()
        }
    }

    /**
     *
     */
    private fun changeGuessIntervalInit() {

        binding.changeGuessIntervall.setOnClickListener() {

            val input = EditText(this)
            input.inputType = InputType.TYPE_CLASS_NUMBER

            val alert = AlertDialog.Builder(this, R.style.MyDialogTheme)

            alert.setTitle("Change interval")
            alert.setView(input)
            alert.setMessage("Change the interval value")
            alert.setPositiveButton("Done") { dialog, _ ->

                if(input.text.toString() != "") {
                    guessInterval = input.text.toString().toInt()

                    if(guessInterval < 1) {

                        guessInterval = 2
                    }
                }

                binding.guessIntervallText.text = "1-" + guessInterval.toString()
            }
            alert.show()
        }
    }

    /**
     *
     */
    private fun setGuessesInit() {

        binding.changeGuessGame.setOnClickListener() {

            if(guessGuesses == 5) {

                guessGuesses = 10
                binding.changeGuessGame.text = "SET 5"
                binding.guessGameText.text = "Guess: 10"
            }

            else {

                guessGuesses = 5
                binding.changeGuessGame.text = "SET 10"
                binding.guessGameText.text = "Guess: 5"
            }
        }
    }

    /**
     *
     */
    private fun saveStats() {

        val sharedPref = getSharedPreferences("statsList", MODE_PRIVATE)
        val editor = sharedPref.edit()

        editor.putInt("listInterval", listInterval)
        editor.putInt("higherInterval", higherInterval)
        editor.putInt("guessInterval", guessInterval)
        editor.putInt("guessGuesses", guessGuesses)
        editor.apply()
    }

    /**
     *
     */
    override fun onBackPressed() {
        super.onBackPressed()
        saveStats()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}