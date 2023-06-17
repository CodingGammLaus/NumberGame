package se.umu.cs.dv21sln.numbergame

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import se.umu.cs.dv21sln.numbergame.databinding.OrderNumberActivityBinding

class OrderNumberActivity  : AppCompatActivity() {

    private lateinit var binding: OrderNumberActivityBinding

    private var numberList = arrayListOf<Int>(0, 0, 0, 0)
    private var guessNumber = arrayListOf<Int>(0, 0, 0, 0)
    private var round = 1
    private var correctPosition = 0
    private var correctNumber = 0
    private var stringGuessNumber = ""
    private var stringRightNumber = ""
    private var stringArray = arrayListOf<String>("0", "0", "0", "0", "0", "0", "0", "0", "0", "0")

    private var played = 0
    private var won = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*Get the view binding*/
        binding = OrderNumberActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadInStats()
        startGame()
        backButtonInit()
    }

    /**
     *
     */
    private fun loadInStats() {

        val sharedPref = getSharedPreferences("statsList", MODE_PRIVATE)
        won = sharedPref.getInt("winsOrder", 0)
        played = sharedPref.getInt("playedOrder", 0)
    }

    /**
     *
     */
    private fun startGame() {

        binding.playButton.setOnClickListener() {

            played++
            slumpNumber()
            binding.guess.isVisible = true
            binding.playButton.text = "GO"

            binding.playButton.setOnClickListener() {
                guessInit()
            }
        }
    }

    /**
     *
     */
    private fun slumpNumber() {

        numberList[0] = (1..9).random()

        do {
            numberList[1] = (1..9).random()
        } while(numberList[1] == numberList[0])

        do {
            numberList[2] = (1..9).random()
        } while(numberList[2] == numberList[0] || numberList[2] == numberList[1])

        do {
            numberList[3] = (1..9).random()
        } while(numberList[3] == numberList[0] || numberList[3] == numberList[1] || numberList[3] == numberList[2])

        stringRightNumber = numberList[0].toString() + "" + numberList[1].toString() + "" +
                numberList[2].toString() + "" + numberList[3].toString()
    }

    /**
     *
     */
    private fun guessInit() {

        if(checkInput()) {

            stringArray[round-1] = stringGuessNumber
            checkNumber()
            makeGuess()
            round++
            binding.guess.text.clear()
            correctNumber = 0
            correctPosition = 0
        }
    }

    /**
     *
     */
    private fun checkInput(): Boolean {

        return binding.guess.text.isNotEmpty() && binding.guess.text.length == 4 && checkDoubles() && checkNew()
    }

    /**
     *
     */
    private fun checkDoubles(): Boolean {

        guessNumber[0] = binding.guess.text[0].toString().toInt()
        guessNumber[1] = binding.guess.text[1].toString().toInt()
        guessNumber[2] = binding.guess.text[2].toString().toInt()
        guessNumber[3] = binding.guess.text[3].toString().toInt()

        if(guessNumber[0] == guessNumber[1] || guessNumber[0] == guessNumber[2] || guessNumber[0] == guessNumber[3]) {

            guessNumber.fill(0)
            return false
        }

        else if(guessNumber[1] == guessNumber[2] || guessNumber[1] == guessNumber[3]) {

            guessNumber.fill(0)
            return false
        }

        else if(guessNumber[2] == guessNumber[3]) {

            guessNumber.fill(0)
            return false
        }

        convertToString()
        return true
    }

    /**
     *
     */
    private fun checkNew(): Boolean {

        for(i in 0..9) {

            if(stringArray[i] == stringGuessNumber) {

                return false
            }
        }

        return true
    }

    /**
     *
     */
    private fun makeGuess() {

        if(round == 1) {

            binding.guess1.text = stringGuessNumber
            binding.number1.text = correctNumber.toString()
            binding.position1.text = correctPosition.toString()
        }

        else if(round == 2) {

            binding.guess2.text = stringGuessNumber
            binding.number2.text = correctNumber.toString()
            binding.position2.text = correctPosition.toString()
        }

        else if(round == 3) {

            binding.guess3.text = stringGuessNumber
            binding.number3.text = correctNumber.toString()
            binding.position3.text = correctPosition.toString()
        }

        else if(round == 4) {

            binding.guess4.text = stringGuessNumber
            binding.number4.text = correctNumber.toString()
            binding.position4.text = correctPosition.toString()
        }

        else if(round == 5) {

            binding.guess5.text = stringGuessNumber
            binding.number5.text = correctNumber.toString()
            binding.position5.text = correctPosition.toString()
        }

        else if(round == 6) {

            binding.guess6.text = stringGuessNumber
            binding.number6.text = correctNumber.toString()
            binding.position6.text = correctPosition.toString()
        }

        else if(round == 7) {

            binding.guess7.text = stringGuessNumber
            binding.number7.text = correctNumber.toString()
            binding.position7.text = correctPosition.toString()
        }

        else if(round == 8) {

            binding.guess8.text = stringGuessNumber
            binding.number8.text = correctNumber.toString()
            binding.position8.text = correctPosition.toString()
        }

        else if(round == 9) {

            binding.guess9.text = stringGuessNumber
            binding.number9.text = correctNumber.toString()
            binding.position9.text = correctPosition.toString()
        }

        else if(round == 10) {

            binding.guess10.text = stringGuessNumber
            binding.number10.text = correctNumber.toString()
            binding.position10.text = correctPosition.toString()
        }
    }

    /**
     *
     */
    private fun convertToString() {

        stringGuessNumber = guessNumber[0].toString() + "" + guessNumber[1].toString() + "" +
                guessNumber[2].toString() + "" + guessNumber[3].toString()
    }

    /**
     *
     */
    private fun checkNumber() {

        for(i in 0..3) {

            if(numberList[i] == guessNumber[i]) {

                correctPosition++
            }

            for(j in 0..3) {

                if(numberList[i] == guessNumber[j]) {

                    correctNumber++
                }
            }
        }

        if(correctPosition == 4) {

            won()
        }

        if(round == 10 && correctPosition != 4) {

            lose()
        }
    }

    /**
     *
     */
    private fun won() {

        won++

        popUpDone("You won", "You guessed the right order!")

        binding.playButton.text = "Popup"
        binding.playButton.setOnClickListener() {

            popUpDone("You won", "You guessed the right order!")
        }
    }

    /**
     *
     */
    private fun lose() {

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

        editor.putInt("playedOrder", played)
        editor.putInt("winsOrder", won)
        editor.apply()
    }

    /**
     *
     */
    private fun popUpDone(title: String, message: String) {

        val alert = AlertDialog.Builder(this, R.style.MyDialogTheme)

        alert.setTitle(title)
        alert.setMessage(message + "\nThe correct number order was: " + stringRightNumber)
        alert.setPositiveButton("Play again") {_, _ ->

            saveStats()
            val intent = Intent(this, OrderNumberActivity::class.java)
            startActivity(intent)
            finish()
        }

        alert.setNegativeButton("Exit") {_, _ ->

            saveStats()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }.create()

        alert.show()
    }

    /**
     * Back init.
     */
    private fun backButtonInit() {

        binding.quitButton.setOnClickListener() {

            binding.quitButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slump_btn))
            popupQuit()
        }
    }

    /**
     *
     */
    private fun popupQuit() {

        val alert = AlertDialog.Builder(this, R.style.MyDialogTheme)

        alert.setTitle("Quit")
        alert.setMessage("Do you want to quit?")
        alert.setPositiveButton("YES") {_, _ ->

            saveStats()
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
        saveStats()
        val intent = Intent(this, PlayActivity::class.java)
        startActivity(intent)
        finish()
    }
}