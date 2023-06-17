package se.umu.cs.dv21sln.numbergame

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import se.umu.cs.dv21sln.numbergame.databinding.List10ActivityBinding
import se.umu.cs.dv21sln.numbergame.databinding.List15ActivityBinding

class List15Activity : AppCompatActivity() {

    private lateinit var binding: List15ActivityBinding
    private var number = 0
    private var numberArray = arrayListOf<Int>(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    private var used = 1
    private var points = 0
    private var interval = 1000

    private var won = 0
    private var played = 0
    private var sixnine = 0
    private var sixsixsix = 0
    private var fourtwenty = 0
    private var restart = false

    private lateinit var adapterItem: ArrayAdapter<String>
    private val menuItemList = arrayListOf<String>("List 5", "List 10", "List 20")

    private var btnUsed = arrayListOf<Boolean>(false, false, false, false, false, false, false,
        false, false, false, false, false, false, false, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*Get the view binding*/
        binding = List15ActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadInStats()
        slumpButtonInit()

        button1Init()
        button2Init()
        button3Init()
        button4Init()
        button5Init()
        button6Init()
        button7Init()
        button8Init()
        button9Init()
        button10Init()
        button11Init()
        button12Init()
        button13Init()
        button14Init()
        button15Init()

        setUpMenu()

        setUpMenu()
        quitButtonInit()
    }

    /**
     *
     */
    private fun loadInStats() {

        val sharedPref = getSharedPreferences("statsList", MODE_PRIVATE)

        interval = sharedPref.getInt("listInterval", 1000)
        won = sharedPref.getInt("winsList15", 0)
        sixnine = sharedPref.getInt("69", 0)
        sixsixsix = sharedPref.getInt("666", 0)
        fourtwenty = sharedPref.getInt("420", 0)
        played = sharedPref.getInt("playedList", 0)

        /*Control to avoid crash*/
        if(interval < 15) {
            interval = 15
        }
    }

    /**
     *
     */
    private fun setUpMenu() {

        adapterItem = ArrayAdapter(this, R.layout.menu_item, menuItemList)
        binding.dropDownMenu.setAdapter(adapterItem)

        binding.dropDownMenu.setOnItemClickListener { _, _, pos, _ ->

            if(pos == 0) {

                val intent = Intent(this, List5Activity::class.java)
                startActivity(intent)
                finish()
            }

            else if (pos == 1) {

                val intent = Intent(this, List10Activity::class.java)
                startActivity(intent)
                finish()
            }

            else if (pos == 2) {

                val intent = Intent(this, List20Activity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    /**
     * Slump button init.
     */
    private fun slumpButtonInit() {

        /*First click*/
        binding.slumpButton.setOnClickListener() {

            played++
            start()

            number = (0..interval).random()
            binding.numberText.text = "Number: " + number.toString()
            binding.numberText.startAnimation(AnimationUtils.loadAnimation(this, R.anim.popout))
            binding.slumpButton.isEnabled = false

            checkStats()

            binding.slumpButton.setOnClickListener() {

                if(binding.slumpButton.isEnabled) {
                    number = (0..interval).random()
                    checkDuplicate()

                    binding.numberText.text = "Number: " + number.toString()
                    binding.numberText.startAnimation(AnimationUtils.loadAnimation(this, R.anim.popout))
                    binding.slumpButton.isEnabled = false
                    used++

                    checkStats()
                }
            }
        }
    }

    /**
     * Reroll if number exist.
     */
    private fun checkDuplicate() {

        for(i in 0..4) {

            if(number == numberArray[i]) {

                number = (0..interval).random()
                checkDuplicate()
            }
        }
    }

    /**
     *
     */
    private fun start() {

        binding.slumpButton.text = "SLUMP"
        binding.button1.isEnabled = true
        binding.button2.isEnabled = true
        binding.button3.isEnabled = true
        binding.button4.isEnabled = true
        binding.button5.isEnabled = true
        binding.button6.isEnabled = true
        binding.button7.isEnabled = true
        binding.button8.isEnabled = true
        binding.button9.isEnabled = true
        binding.button10.isEnabled = true
        binding.button11.isEnabled = true
        binding.button12.isEnabled = true
        binding.button13.isEnabled = true
        binding.button14.isEnabled = true
        binding.button15.isEnabled = true
    }

    /**
     * Button 1 init.
     */
    private fun button1Init() {

        binding.button1.setOnClickListener() {

            if(!binding.slumpButton.isEnabled) {

                binding.button1.isEnabled = false
                binding.button1.text = number.toString()
                binding.slumpButton.isEnabled = true

                for(i in 1..14) {

                    if(number > numberArray[i] && btnUsed[i]) {

                        binding.button1.background = resources.getDrawable(R.drawable.wrong_button)

                        checkIfDone()
                        checkIfRestart(0)
                        return@setOnClickListener
                    }
                }

                numberArray[0] = number
                binding.button1.background = resources.getDrawable(R.drawable.correct_button)
                points++
                btnUsed[0] = true
            }

            checkIfDone()
        }
    }

    /**
     *
     */
    private fun button2Init() {

        binding.button2.setOnClickListener() {

            if(!binding.slumpButton.isEnabled) {

                binding.button2.isEnabled = false
                binding.button2.text = number.toString()
                binding.slumpButton.isEnabled = true

                if(number < numberArray[0] && btnUsed[0]) {

                    binding.button2.background = resources.getDrawable(R.drawable.wrong_button)

                    checkIfDone()
                    checkIfRestart(1)
                    return@setOnClickListener
                }

                for(i in 2..14) {

                    if(number > numberArray[i] && btnUsed[i]) {

                        binding.button2.background = resources.getDrawable(R.drawable.wrong_button)

                        checkIfDone()
                        checkIfRestart(1)
                        return@setOnClickListener
                    }
                }

                numberArray[1] = number
                binding.button2.background = resources.getDrawable(R.drawable.correct_button)
                points++
                btnUsed[1] = true
            }

            checkIfDone()
        }
    }

    /**
     *
     */
    private fun button3Init() {

        binding.button3.setOnClickListener() {

            if(!binding.slumpButton.isEnabled) {

                binding.button3.isEnabled = false
                binding.button3.text = number.toString()
                binding.slumpButton.isEnabled = true

                for(i in 0..1) {

                    if(number < numberArray[i] && btnUsed[i]) {

                        binding.button3.background = resources.getDrawable(R.drawable.wrong_button)

                        checkIfDone()
                        checkIfRestart(2)
                        return@setOnClickListener
                    }
                }

                for(i in 3..14) {

                    if(number > numberArray[i] && btnUsed[i]) {

                        binding.button3.background = resources.getDrawable(R.drawable.wrong_button)

                        checkIfDone()
                        checkIfRestart(2)
                        return@setOnClickListener
                    }
                }

                numberArray[2] = number
                binding.button3.background = resources.getDrawable(R.drawable.correct_button)
                points++
                btnUsed[2] = true
            }

            checkIfDone()
        }
    }

    /**
     *
     */
    private fun button4Init() {

        binding.button4.setOnClickListener() {

            if(!binding.slumpButton.isEnabled) {

                binding.button4.isEnabled = false
                binding.button4.text = number.toString()
                binding.slumpButton.isEnabled = true

                for(i in 0..2) {

                    if(number < numberArray[i] && btnUsed[i]) {

                        binding.button4.background = resources.getDrawable(R.drawable.wrong_button)

                        checkIfDone()
                        checkIfRestart(3)
                        return@setOnClickListener
                    }
                }

                for(i in 4..14) {

                    if(number > numberArray[i] && btnUsed[i]) {

                        binding.button4.background = resources.getDrawable(R.drawable.wrong_button)

                        checkIfDone()
                        checkIfRestart(3)
                        return@setOnClickListener
                    }
                }

                numberArray[3] = number
                binding.button4.background = resources.getDrawable(R.drawable.correct_button)
                points++
                btnUsed[3] = true
            }

            checkIfDone()
        }
    }

    /**
     *
     */
    private fun button5Init() {

        binding.button5.setOnClickListener() {

            if(!binding.slumpButton.isEnabled) {

                binding.button5.isEnabled = false
                binding.button5.text = number.toString()
                binding.slumpButton.isEnabled = true

                for(i in 0..3) {

                    if(number < numberArray[i] && btnUsed[i]) {

                        binding.button5.background = resources.getDrawable(R.drawable.wrong_button)

                        checkIfDone()
                        checkIfRestart(4)
                        return@setOnClickListener
                    }
                }

                for(i in 5..14) {

                    if(number > numberArray[i] && btnUsed[i]) {

                        binding.button5.background = resources.getDrawable(R.drawable.wrong_button)

                        checkIfDone()
                        checkIfRestart(4)
                        return@setOnClickListener
                    }
                }

                numberArray[4] = number
                binding.button5.background = resources.getDrawable(R.drawable.correct_button)
                points++
                btnUsed[4] = true
            }

            checkIfDone()
        }
    }

    /**
     *
     */
    private fun button6Init() {

        binding.button6.setOnClickListener() {

            if(!binding.slumpButton.isEnabled) {

                binding.button6.isEnabled = false
                binding.button6.text = number.toString()
                binding.slumpButton.isEnabled = true

                for(i in 0..4) {

                    if(number < numberArray[i] && btnUsed[i]) {

                        binding.button6.background = resources.getDrawable(R.drawable.wrong_button)

                        checkIfDone()
                        checkIfRestart(5)
                        return@setOnClickListener
                    }
                }

                for(i in 6..14) {

                    if(number > numberArray[i] && btnUsed[i]) {

                        binding.button6.background = resources.getDrawable(R.drawable.wrong_button)

                        checkIfDone()
                        checkIfRestart(5)
                        return@setOnClickListener
                    }
                }

                numberArray[5] = number
                binding.button6.background = resources.getDrawable(R.drawable.correct_button)
                points++
                btnUsed[5] = true
            }

            checkIfDone()
        }
    }

    /**
     *
     */
    private fun button7Init() {

        binding.button7.setOnClickListener() {

            if(!binding.slumpButton.isEnabled) {

                binding.button7.isEnabled = false
                binding.button7.text = number.toString()
                binding.slumpButton.isEnabled = true

                for(i in 0..5) {

                    if(number < numberArray[i] && btnUsed[i]) {

                        binding.button7.background = resources.getDrawable(R.drawable.wrong_button)

                        checkIfDone()
                        checkIfRestart(6)
                        return@setOnClickListener
                    }
                }

                for(i in 7..14) {

                    if(number > numberArray[i] && btnUsed[i]) {

                        binding.button7.background = resources.getDrawable(R.drawable.wrong_button)

                        checkIfDone()
                        checkIfRestart(6)
                        return@setOnClickListener
                    }
                }

                numberArray[6] = number
                binding.button7.background = resources.getDrawable(R.drawable.correct_button)
                points++
                btnUsed[6] = true
            }

            checkIfDone()
        }
    }

    /**
     *
     */
    private fun button8Init() {

        binding.button8.setOnClickListener() {

            if(!binding.slumpButton.isEnabled) {

                binding.button8.isEnabled = false
                binding.button8.text = number.toString()
                binding.slumpButton.isEnabled = true

                for(i in 0..6) {

                    if(number < numberArray[i] && btnUsed[i]) {

                        binding.button8.background = resources.getDrawable(R.drawable.wrong_button)

                        checkIfDone()
                        checkIfRestart(7)
                        return@setOnClickListener
                    }
                }

                for(i in 8..14) {

                    if(number > numberArray[i] && btnUsed[i]) {

                        binding.button8.background = resources.getDrawable(R.drawable.wrong_button)

                        checkIfDone()
                        checkIfRestart(7)
                        return@setOnClickListener
                    }
                }

                numberArray[7] = number
                binding.button8.background = resources.getDrawable(R.drawable.correct_button)
                points++
                btnUsed[7] = true
            }

            checkIfDone()
        }
    }

    /**
     *
     */
    private fun button9Init() {

        binding.button9.setOnClickListener() {

            if(!binding.slumpButton.isEnabled) {

                binding.button9.isEnabled = false
                binding.button9.text = number.toString()
                binding.slumpButton.isEnabled = true

                for(i in 0..7) {

                    if(number < numberArray[i] && btnUsed[i]) {

                        binding.button9.background = resources.getDrawable(R.drawable.wrong_button)

                        checkIfDone()
                        checkIfRestart(8)
                        return@setOnClickListener
                    }
                }

                for(i in 9..14) {

                    if(number > numberArray[i] && btnUsed[i]) {

                        binding.button9.background = resources.getDrawable(R.drawable.wrong_button)

                        checkIfDone()
                        checkIfRestart(8)
                        return@setOnClickListener
                    }
                }

                numberArray[8] = number
                binding.button9.background = resources.getDrawable(R.drawable.correct_button)
                points++
                btnUsed[8] = true
            }

            checkIfDone()
        }
    }

    /**
     *
     */
    private fun button10Init() {

        binding.button10.setOnClickListener() {

            if(!binding.slumpButton.isEnabled) {

                binding.button10.isEnabled = false
                binding.button10.text = number.toString()
                binding.slumpButton.isEnabled = true

                for(i in 0..8) {

                    if(number < numberArray[i] && btnUsed[i]) {

                        binding.button10.background = resources.getDrawable(R.drawable.wrong_button)

                        checkIfDone()
                        checkIfRestart(9)
                        return@setOnClickListener
                    }
                }

                for(i in 10..14) {

                    if(number > numberArray[i] && btnUsed[i]) {

                        binding.button10.background = resources.getDrawable(R.drawable.wrong_button)

                        checkIfDone()
                        checkIfRestart(9)
                        return@setOnClickListener
                    }
                }

                numberArray[9] = number
                binding.button10.background = resources.getDrawable(R.drawable.correct_button)
                points++
                btnUsed[9] = true
            }

            checkIfDone()
        }
    }

    /**
     *
     */
    private fun button11Init() {

        binding.button11.setOnClickListener() {

            if(!binding.slumpButton.isEnabled) {

                binding.button11.isEnabled = false
                binding.button11.text = number.toString()
                binding.slumpButton.isEnabled = true

                for(i in 0..9) {

                    if(number < numberArray[i] && btnUsed[i]) {

                        binding.button11.background = resources.getDrawable(R.drawable.wrong_button)

                        checkIfDone()
                        checkIfRestart(10)
                        return@setOnClickListener
                    }
                }

                for(i in 11..14) {

                    if(number > numberArray[i] && btnUsed[i]) {

                        binding.button11.background = resources.getDrawable(R.drawable.wrong_button)

                        checkIfDone()
                        checkIfRestart(10)
                        return@setOnClickListener
                    }
                }

                numberArray[10] = number
                binding.button11.background = resources.getDrawable(R.drawable.correct_button)
                points++
                btnUsed[10] = true
            }

            checkIfDone()
        }
    }

    /**
     *
     */
    private fun button12Init() {

        binding.button12.setOnClickListener() {

            if(!binding.slumpButton.isEnabled) {

                binding.button12.isEnabled = false
                binding.button12.text = number.toString()
                binding.slumpButton.isEnabled = true

                for(i in 0..10) {

                    if(number < numberArray[i] && btnUsed[i]) {

                        binding.button12.background = resources.getDrawable(R.drawable.wrong_button)

                        checkIfDone()
                        checkIfRestart(11)
                        return@setOnClickListener
                    }
                }

                for(i in 12..14) {

                    if(number > numberArray[i] && btnUsed[i]) {

                        binding.button12.background = resources.getDrawable(R.drawable.wrong_button)

                        checkIfDone()
                        checkIfRestart(11)
                        return@setOnClickListener
                    }
                }

                numberArray[11] = number
                binding.button12.background = resources.getDrawable(R.drawable.correct_button)
                points++
                btnUsed[11] = true
            }

            checkIfDone()
        }
    }

    /**
     *
     */
    private fun button13Init() {

        binding.button13.setOnClickListener() {

            if(!binding.slumpButton.isEnabled) {

                binding.button13.isEnabled = false
                binding.button13.text = number.toString()
                binding.slumpButton.isEnabled = true

                for(i in 0..11) {

                    if(number < numberArray[i] && btnUsed[i]) {

                        binding.button13.background = resources.getDrawable(R.drawable.wrong_button)

                        checkIfDone()
                        checkIfRestart(12)
                        return@setOnClickListener
                    }
                }

                for(i in 13..14) {

                    if(number > numberArray[i] && btnUsed[i]) {

                        binding.button13.background = resources.getDrawable(R.drawable.wrong_button)

                        checkIfDone()
                        checkIfRestart(12)
                        return@setOnClickListener
                    }
                }

                numberArray[12] = number
                binding.button13.background = resources.getDrawable(R.drawable.correct_button)
                points++
                btnUsed[12] = true
            }

            checkIfDone()
        }
    }

    /**
     *
     */
    private fun button14Init() {

        binding.button14.setOnClickListener() {

            if(!binding.slumpButton.isEnabled) {

                binding.button14.isEnabled = false
                binding.button14.text = number.toString()
                binding.slumpButton.isEnabled = true

                for(i in 0..12) {

                    if(number < numberArray[i] && btnUsed[i]) {

                        binding.button14.background = resources.getDrawable(R.drawable.wrong_button)

                        checkIfDone()
                        checkIfRestart(13)
                        return@setOnClickListener
                    }
                }

                if(number > numberArray[14] && btnUsed[14]) {

                    binding.button14.background = resources.getDrawable(R.drawable.wrong_button)

                    checkIfDone()
                    checkIfRestart(13)
                    return@setOnClickListener
                }

                numberArray[9] = number
                binding.button14.background = resources.getDrawable(R.drawable.correct_button)
                points++
                btnUsed[9] = true
            }

            checkIfDone()
        }
    }

    /**
     *
     */
    private fun button15Init() {

        binding.button15.setOnClickListener() {

            if(!binding.slumpButton.isEnabled) {

                binding.button15.isEnabled = false
                binding.button15.text = number.toString()
                binding.slumpButton.isEnabled = true

                for(i in 0..13) {

                    if(number < numberArray[i] && btnUsed[i]) {

                        binding.button15.background = resources.getDrawable(R.drawable.wrong_button)

                        checkIfDone()
                        checkIfRestart(14)
                        return@setOnClickListener
                    }
                }

                numberArray[14] = number
                binding.button15.background = resources.getDrawable(R.drawable.correct_button)
                points++
                btnUsed[14] = true
            }

            checkIfDone()
        }
    }

    /**
     * Check stats.
     */
    private fun checkStats() {

        if(number == 666) {
            sixsixsix++
        }

        else if(number == 69) {
            sixnine++
        }

        else if(number == 420) {
            fourtwenty++
        }
    }

    /**
     *
     */
    private fun checkIfDone() {

        if(used == 15) {

            result()
        }
    }

    /**
     *
     */
    private fun checkIfRestart(i: Int) {

        if(!btnUsed[i] && !restart) {

            popupRestart()
            restart = true
        }
    }

    /**
     *
     */
    private fun checkIfWon() {

        if(points == 15) {

            won++
            binding.numberText.text = "WON!"
            popupWin()
        }

        else {

            binding.numberText.text = "Lose"
            popupLose()
        }
    }

    /**
     * Result.
     */
    private fun result() {

        binding.slumpButton.text = "RESULT"
        checkIfWon()

        /*Last click*/
        binding.slumpButton.setOnClickListener() {

            checkIfWon()
        }
    }

    /**
     *
     */
    private fun popupWin() {

        val alert = AlertDialog.Builder(this, R.style.MyDialogTheme)

        alert.setTitle("Ezzz")
        alert.setMessage("Congratz! You won!")
        alert.setPositiveButton("Play Again") { dialog, _ ->

            saveStats()
            dialog.cancel()

            val intent = Intent(this, List15Activity::class.java)
            startActivity(intent)
            finish()
        }

        alert.setNegativeButton("Exit") {_, _ ->

            quit()
        }.create()

        alert.show()
    }

    /**
     *
     */
    private fun popupLose() {

        val alert = AlertDialog.Builder(this, R.style.MyDialogTheme)

        alert.setTitle("LOSER")
        alert.setMessage("You loss")
        alert.setPositiveButton("Play Again") { dialog, _ ->

            dialog.cancel()
            saveStats()

            val intent = Intent(this, List15Activity::class.java)
            startActivity(intent)
            finish()
        }

        alert.setNegativeButton("Exit") {_, _ ->

            quit()
        }.create()

        alert.show()
    }

    /**
     *
     */
    private fun popupRestart() {

        val alert = AlertDialog.Builder(this, R.style.MyDialogTheme)

        alert.setTitle("RESTART OR CONTINUE")
        alert.setMessage("You loss. Do you want to restart or continue?")
        alert.setPositiveButton("Restart") { dialog, _ ->

            val intent = Intent(this, List15Activity::class.java)
            startActivity(intent)
            finish()
        }

        alert.setNegativeButton("Continue") {_, _ ->

            closeContextMenu()
        }.create()

        alert.show()
    }

    /**
     * Save stats.
     */
    private fun saveStats() {

        val sharedPref = getSharedPreferences("statsList", MODE_PRIVATE)
        val editor = sharedPref.edit()

        editor.putInt("winsList15", won)
        editor.putInt("69", sixnine)
        editor.putInt("666", sixsixsix)
        editor.putInt("420", fourtwenty)
        editor.putInt("playedList", played)
        editor.apply()
    }

    /**
     *
     */
    private fun quitButtonInit() {

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

            quit()
        }

        alert.setNegativeButton("NO") {_, _ ->

            closeContextMenu()
        }.create()

        alert.show()
    }

    /**
     * Quit.
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