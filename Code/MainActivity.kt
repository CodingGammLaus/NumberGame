package se.umu.cs.dv21sln.numbergame

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.view.View.OnTouchListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.view.animation.AnimationUtils
import se.umu.cs.dv21sln.numbergame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*Get the view binding*/
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playInit()
        statsInit()
        settingsInit()
    }

    /**
     * Play init.
     */
    private fun playInit() {

        playPressed()

        binding.playButton.setOnClickListener() {

            binding.settingsButton.isEnabled = false
            binding.statsButton.isEnabled = false
            binding.playButton.isEnabled = false

            Handler().postDelayed(Runnable {
                val intent = Intent(this, PlayActivity::class.java)
                startActivity(intent)
                finish()
            }, 200)
        }
    }

    /**
     *
     */
    @SuppressLint("ClickableViewAccessibility")
    private fun playPressed() {

        binding.playButton.setOnTouchListener(OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding.playButtonIcon.startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_pressed))
                    binding.playButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.play_zoom_pressed))
                }

                MotionEvent.ACTION_UP -> {
                    binding.playButtonIcon.startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_relese))
                    binding.playButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.play_zoom_relese))
                }
            }
            false
        })
    }

    /**
     * Stats init.
     */
    private fun statsInit() {

        statsPressed()

        binding.statsButton.setOnClickListener() {

            binding.settingsButton.isEnabled = false
            binding.statsButton.isEnabled = false
            binding.playButton.isEnabled = false

            Handler().postDelayed(Runnable {
                val intent = Intent(this, StatsActivity::class.java)
                startActivity(intent)
                finish()
            }, 500)

            binding.statsButtonIcon.startAnimation(AnimationUtils.loadAnimation(this, R.anim.spin))
        }
    }

    /**
     *
     */
    @SuppressLint("ClickableViewAccessibility")
    private fun statsPressed() {

        binding.statsButton.setOnTouchListener(OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN ->
                    binding.statsButtonIcon.startAnimation(AnimationUtils.loadAnimation(this, R.anim.spin_pressed))

                MotionEvent.ACTION_UP ->
                    binding.statsButtonIcon.startAnimation(AnimationUtils.loadAnimation(this, R.anim.spin_relese))
            }
            false
        })
    }

    /**
     * Settings init.
     */
    private fun settingsInit() {

        settingsPressed()

        binding.settingsButton.setOnClickListener() {

            binding.settingsButton.isEnabled = false
            binding.statsButton.isEnabled = false
            binding.playButton.isEnabled = false

            Handler().postDelayed(Runnable {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                finish()
            }, 500)

            binding.settingsButtonIcon.startAnimation(AnimationUtils.loadAnimation(this, R.anim.spin))
        }
    }

    /**
     *
     */
    @SuppressLint("ClickableViewAccessibility")
    private fun settingsPressed() {

        binding.settingsButton.setOnTouchListener(OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN ->
                    binding.settingsButtonIcon.startAnimation(AnimationUtils.loadAnimation(this, R.anim.spin_pressed))

                MotionEvent.ACTION_UP ->
                    binding.settingsButtonIcon.startAnimation(AnimationUtils.loadAnimation(this, R.anim.spin_relese))
            }
            false
        })
    }

    /**
     *
     */
    override fun onBackPressed() {

        val alert = AlertDialog.Builder(this, R.style.MyDialogTheme)

        alert.setTitle("Quit")
        alert.setMessage("Do you want to quit the game?")
        alert.setPositiveButton("YES") {_, _ ->

            finish()
        }

        alert.setNegativeButton("NO") {_, _ ->

            closeContextMenu()
        }.create()

        alert.show()
    }
}