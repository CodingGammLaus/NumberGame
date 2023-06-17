package se.umu.cs.dv21sln.numbergame

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import se.umu.cs.dv21sln.numbergame.databinding.List5ActivityBinding

class List5Activity : AppCompatActivity() {

    private lateinit var binding: List5ActivityBinding

    private var number = 0
    private var numberArray = arrayListOf<Int>(0, 0, 0, 0, 0)
    private var used = 1
    private var points = 0
    private var interval = 1000

    private var btnUsed = arrayListOf<Boolean>(false, false, false, false, false)
    private lateinit var win: MediaPlayer
    private var won = 0
    private var played = 0
    private var sixnine = 0
    private var sixsixsix = 0
    private var fourtwenty = 0
    private var elite = 0
    private var restart = false
    private var reroll = 0
    private var canReroll = true

    private lateinit var adapterItem: ArrayAdapter<String>
    private val menuItemList = arrayListOf<String>("List 10", "List 15", "List 20")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*Get the view binding*/
        binding = List5ActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        win = MediaPlayer.create(this, R.raw.easy)

        loadInStats()
        setUpMenu()
        slumpButtonInit()

        button1Init()
        button2Init()
        button3Init()
        button4Init()
        button5Init()

        quitButtonInit()
    }

    /**
     *
     */
    private fun loadInStats() {

        val sharedPref = getSharedPreferences("statsList", MODE_PRIVATE)

        interval = sharedPref.getInt("listInterval", 1000)

        won = sharedPref.getInt("winsList5", 0)
        played = sharedPref.getInt("playedList", 0)
        sixnine = sharedPref.getInt("69", 0)
        sixsixsix = sharedPref.getInt("666", 0)
        fourtwenty = sharedPref.getInt("420", 0)
        elite = sharedPref.getInt("elite", 0)
        reroll = sharedPref.getInt("reroll", 0)

        /*Control to avoid crash*/
        if(interval < 5) {
            interval = 5
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

                val intent = Intent(this, List10Activity::class.java)
                startActivity(intent)
                finish()
            }

            else if (pos == 1) {

                val intent = Intent(this, List15Activity::class.java)
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

            binding.slumpButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slump_btn))
            played++

            binding.slumpButton.text = "ReRoll x " + reroll.toString()
            binding.button1.isEnabled = true
            binding.button2.isEnabled = true
            binding.button3.isEnabled = true
            binding.button4.isEnabled = true
            binding.button5.isEnabled = true

            number = (0..interval).random()
            binding.numberText.text = "Number: " + number.toString()
            binding.numberText.startAnimation(AnimationUtils.loadAnimation(this, R.anim.popout))

            checkStats()

            if(reroll < 1) {

                canReroll = false
                binding.slumpButton.isEnabled = false
                binding.slumpButton.text = "SLUMP"
            }

            binding.slumpButton.setOnClickListener() {

                binding.slumpButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slump_btn))
                roll()
            }
        }
    }

    /**
     *
     */
    private fun roll() {

        if(binding.slumpButton.isEnabled && !canReroll) {

            number = (0..interval).random()
            checkDuplicate()

            binding.numberText.text = "Number: " + number.toString()
            binding.numberText.startAnimation(AnimationUtils.loadAnimation(this, R.anim.popout))
            used++
            canReroll = true

            if(reroll > 0) {

                binding.slumpButton.text = "ReRoll x " + reroll.toString()
            }

            checkStats()
        }

        else if(canReroll) {

            reroll()
        }

        if(reroll < 1) {

            canReroll = false
            binding.slumpButton.isEnabled = false
            binding.slumpButton.text = "SLUMP"
        }
    }

    /**
     *
     */
    private fun reroll() {

        number = (0..interval).random()
        checkDuplicate()

        binding.numberText.text = "Number: " + number.toString()
        binding.numberText.startAnimation(AnimationUtils.loadAnimation(this, R.anim.popout))
        reroll--

        binding.slumpButton.text = "ReRoll x " + reroll.toString()

        checkStats()
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

        else if(number == 1337) {
            elite++
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
     * Button 1 init.
     */
    private fun button1Init() {

        binding.button1.setOnClickListener() {

            if(!binding.slumpButton.isEnabled || canReroll) {

                if(number > numberArray[1] && btnUsed[1]) {

                    binding.button1.background = resources.getDrawable(R.drawable.wrong_button)
                }

                else if(number > numberArray[2] && btnUsed[2]) {

                    binding.button1.background = resources.getDrawable(R.drawable.wrong_button)
                }

                else if(number > numberArray[3] && btnUsed[3]) {

                    binding.button1.background = resources.getDrawable(R.drawable.wrong_button)
                }

                else if(number > numberArray[4] && btnUsed[4]) {

                    binding.button1.background = resources.getDrawable(R.drawable.wrong_button)
                }

                else {

                    numberArray[0] = number
                    binding.button1.background = resources.getDrawable(R.drawable.correct_button)
                    points++
                    btnUsed[0] = true
                }

                binding.button1.isEnabled = false
                binding.number1.startAnimation(AnimationUtils.loadAnimation(this, R.anim.drop_number))
                binding.button1.text = number.toString()
                setUpSlumpBtn()
                checkIfRestart(0)
            }

            checkIfDone()
        }
    }

    /**
     * Button 2 init.
     */
    private fun button2Init() {

        binding.button2.setOnClickListener() {

            if(!binding.slumpButton.isEnabled || canReroll) {

                if(number < numberArray[0] && btnUsed[0]) {

                    binding.button2.background = resources.getDrawable(R.drawable.wrong_button)
                }

                else if(number > numberArray[2] && btnUsed[2]) {

                    binding.button2.background = resources.getDrawable(R.drawable.wrong_button)
                }

                else if(number > numberArray[3] && btnUsed[3]) {

                    binding.button2.background = resources.getDrawable(R.drawable.wrong_button)
                }

                else if(number > numberArray[4] && btnUsed[4]) {

                    binding.button2.background = resources.getDrawable(R.drawable.wrong_button)
                }

                else {

                    numberArray[1] = number
                    binding.button2.background = resources.getDrawable(R.drawable.correct_button)
                    points++
                    btnUsed[1] = true
                }

                binding.button2.isEnabled = false
                binding.button2.text = number.toString()
                binding.number2.startAnimation(AnimationUtils.loadAnimation(this, R.anim.drop_number))
                setUpSlumpBtn()
                checkIfRestart(1)
            }

            checkIfDone()
        }
    }

    /**
     * Button 3 init.
     */
    private fun button3Init() {

        binding.button3.setOnClickListener() {

            if(!binding.slumpButton.isEnabled || canReroll) {

                if(number < numberArray[0] && btnUsed[0]) {

                    binding.button3.background = resources.getDrawable(R.drawable.wrong_button)
                }

                else if(number < numberArray[1] && btnUsed[1]) {

                    binding.button3.background = resources.getDrawable(R.drawable.wrong_button)
                }

                else if(number > numberArray[3] && btnUsed[3]) {

                    binding.button3.background = resources.getDrawable(R.drawable.wrong_button)
                }

                else if(number > numberArray[4] && btnUsed[4]) {

                    binding.button3.background = resources.getDrawable(R.drawable.wrong_button)
                }

                else {

                    numberArray[2] = number
                    binding.button3.background = resources.getDrawable(R.drawable.correct_button)
                    points++
                    btnUsed[2] = true
                }

                binding.button3.isEnabled = false
                binding.button3.text = number.toString()
                binding.number3.startAnimation(AnimationUtils.loadAnimation(this, R.anim.drop_number))
                setUpSlumpBtn()
                checkIfRestart(2)
            }

            checkIfDone()
        }
    }

    /**
     * Button 4 init.
     */
    private fun button4Init() {

        binding.button4.setOnClickListener() {

            if(!binding.slumpButton.isEnabled || canReroll) {

                if(number < numberArray[0] && btnUsed[0]) {

                    binding.button4.background = resources.getDrawable(R.drawable.wrong_button)
                }

                else if(number < numberArray[1] && btnUsed[1]) {

                    binding.button4.background = resources.getDrawable(R.drawable.wrong_button)
                }

                else if(number < numberArray[2] && btnUsed[2]) {

                    binding.button4.background = resources.getDrawable(R.drawable.wrong_button)
                }

                else if(number > numberArray[4] && btnUsed[4]) {

                    binding.button4.background = resources.getDrawable(R.drawable.wrong_button)
                }

                else {

                    numberArray[3] = number
                    binding.button4.background = resources.getDrawable(R.drawable.correct_button)
                    points++
                    btnUsed[3] = true
                }

                binding.button4.isEnabled = false
                binding.button4.text = number.toString()
                binding.number4.startAnimation(AnimationUtils.loadAnimation(this, R.anim.drop_number))
                setUpSlumpBtn()
                checkIfRestart(3)
            }

            checkIfDone()
        }
    }

    /**
     * Button 5 init.
     */
    private fun button5Init() {

        binding.button5.setOnClickListener() {

            if(!binding.slumpButton.isEnabled || canReroll) {

                if(number < numberArray[0] && btnUsed[0]) {

                    binding.button5.background = resources.getDrawable(R.drawable.wrong_button)
                }

                else if(number < numberArray[1] && btnUsed[1]) {

                    binding.button5.background = resources.getDrawable(R.drawable.wrong_button)
                }

                else if(number < numberArray[2] && btnUsed[2]) {

                    binding.button5.background = resources.getDrawable(R.drawable.wrong_button)
                }

                else if(number < numberArray[3] && btnUsed[3]) {

                    binding.button5.background = resources.getDrawable(R.drawable.wrong_button)
                }

                else {

                    numberArray[4] = number
                    binding.button5.background = resources.getDrawable(R.drawable.correct_button)
                    points++
                    btnUsed[4] = true
                }

                binding.button5.isEnabled = false
                binding.button5.text = number.toString()
                binding.number5.startAnimation(AnimationUtils.loadAnimation(this, R.anim.drop_number))
                setUpSlumpBtn()
                checkIfRestart(4)
            }

            checkIfDone()
        }
    }

    /**
     *
     */
    private fun setUpSlumpBtn() {

        binding.slumpButton.text = "SLUMP"
        binding.slumpButton.isEnabled = true
        canReroll = false
    }

    /**
     *
     */
    private fun checkIfDone() {

        if(used == 5) {

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

        restart = true

        if(points == 5) {

            won++
            binding.numberText.text = "WON!"
            win.start()
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
            win.stop()

            val intent = Intent(this, List5Activity::class.java)
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

            saveStats()
            val intent = Intent(this, List5Activity::class.java)
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

            saveStats()
            val intent = Intent(this, List5Activity::class.java)
            startActivity(intent)
            finish()
        }

        alert.setNegativeButton("Continue") {_, _ ->

            closeContextMenu()
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

            quit()
        }

        alert.setNegativeButton("NO") {_, _ ->

            closeContextMenu()
        }.create()

        alert.show()
    }

    /**
     * Quit button init.
     */
    private fun quitButtonInit() {

        binding.quitButton.setOnClickListener() {

            binding.quitButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slump_btn))
            popupQuit()
        }
    }

    /**
     * Save stats.
     */
    private fun saveStats() {

        val sharedPref = getSharedPreferences("statsList", MODE_PRIVATE)
        val editor = sharedPref.edit()

        editor.putInt("winsList5", won)
        editor.putInt("playedList", played)
        editor.putInt("69", sixnine)
        editor.putInt("666", sixsixsix)
        editor.putInt("420", fourtwenty)
        editor.putInt("1337", elite)
        editor.apply()
    }

    /**
     * Quit.
     */
    private fun quit() {

        saveStats()
        win.stop()
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