package se.umu.cs.dv21sln.numbergame

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import se.umu.cs.dv21sln.numbergame.databinding.SettingsActivityBinding


class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: SettingsActivityBinding

    private var listInterval = 1000
    private var higherInterval = 1000
    private var guessInterval = 100
    private var reroll = 0
    private var lives = 0

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
        resetSettingsBtnInit()
        seekBar()
        livesSeekBar()
    }

    /**
     * Load in the stats.
     */
    private fun loadInStats() {

        val sharedPref = getSharedPreferences("statsList", MODE_PRIVATE)

        listInterval = sharedPref.getInt("listInterval", 1000)
        higherInterval = sharedPref.getInt("higherInterval", 1000)
        guessInterval = sharedPref.getInt("guessInterval", 100)
        reroll = sharedPref.getInt("reroll", 0)
        lives = sharedPref.getInt("lives", 0)

        binding.listIntervallText.text = "0-" + listInterval.toString()
        binding.higherIntervallText.text = "0-" + higherInterval.toString()
        binding.guessIntervallText.text = "0-" + guessInterval.toString()
        binding.rerollText.text = "Reroll: " + reroll.toString()
        binding.seekBar.progress = reroll
        binding.livesText.text = "Lives: " + lives.toString()
        binding.seekBarLives.progress = lives
    }

    /**
     * Back button init.
     */
    private fun backButtonInit() {

        binding.backButton.setOnClickListener() {

            binding.backButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slump_btn))
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

                        listInterval = 1
                    }
                }

                binding.listIntervallText.text = "0-" + listInterval.toString()
            }
            alert.show()
        }
    }

    /**
     *
     */
    private fun seekBar() {

        binding.seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {

                binding.rerollText.text = "Reroll: " + progress

                reroll = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }

    /**
     *
     */
    private fun livesSeekBar() {

        binding.seekBarLives.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {

                binding.livesText.text = "Lives: " + progress

                lives = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
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

                        higherInterval = 1
                    }
                }

                binding.higherIntervallText.text = "0-" + higherInterval.toString()
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

                        guessInterval = 1
                    }
                }

                binding.guessIntervallText.text = "0-" + guessInterval.toString()
            }
            alert.show()
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
        editor.putInt("reroll", reroll)
        editor.putInt("lives", lives)
        editor.apply()
    }

    /**
     *
     */
    private fun resetSettingsBtnInit() {

       binding.resetButton.setOnClickListener() {

           val alert = android.app.AlertDialog.Builder(this, R.style.MyDialogTheme)

           alert.setTitle("Reset settings")
           alert.setMessage("Do you want to reset the settings?")
           alert.setPositiveButton("YES") {_, _ ->

               shake()
               resetSettings()
           }

           alert.setNegativeButton("NO") {_, _ ->

               closeContextMenu()
           }.create()

           alert.show()
       }
    }

    /**
     *
     */
    private fun shake() {

        binding.box1.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake))
        binding.box2.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake))
        binding.box3.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake))
    }

    /**
     *
     */
    private fun resetSettings() {

        listInterval = 1000
        higherInterval = 1000
        guessInterval = 100
        reroll = 0
        lives = 0

        binding.listIntervallText.text = "0-" + listInterval.toString()
        binding.higherIntervallText.text = "0-" + higherInterval.toString()
        binding.guessIntervallText.text = "0-" + guessInterval.toString()
        binding.seekBar.progress = reroll
        binding.seekBarLives.progress = lives
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