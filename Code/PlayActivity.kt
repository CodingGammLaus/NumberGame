package se.umu.cs.dv21sln.numbergame

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View.OnTouchListener
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import se.umu.cs.dv21sln.numbergame.databinding.PlayActivityBinding


class PlayActivity : AppCompatActivity() {

    private lateinit var binding: PlayActivityBinding
    private val loadtime = 650L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*Get the view binding*/
        binding = PlayActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listInit()
        higherInit()
        guessInit()
        orderInit()
    }

    /**
     * List init.
     */
    private fun listInit() {

        listPressed()

        binding.listButton.setOnClickListener() {

            binding.higherButton.isEnabled = false
            binding.guessButton.isEnabled = false
            binding.orderButton.isEnabled = false
            binding.listButton.isEnabled = false

            Handler().postDelayed(Runnable {
                val intent = Intent(this, List5Activity::class.java)
                startActivity(intent)
                finish()
            }, loadtime)

            binding.listButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.swipeout_right))
            binding.higherButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.disapear))
            binding.guessButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.disapear))
            binding.orderButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.disapear))
        }
    }

    /**
     *
     */
    @SuppressLint("ClickableViewAccessibility")
    private fun listPressed() {

        binding.listButton.setOnTouchListener(OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN ->
                    binding.listButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_pressed))

                MotionEvent.ACTION_UP ->
                    binding.listButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_relese))
            }
            false
        })
    }

    /**
     * Higher init.
     */
    private fun higherInit() {

        higherPressed()

        binding.higherButton.setOnClickListener() {

            binding.listButton.isEnabled = false
            binding.guessButton.isEnabled = false
            binding.orderButton.isEnabled = false
            binding.higherButton.isEnabled = false

            Handler().postDelayed(Runnable {
                val intent = Intent(this, HigherLowerActivity::class.java)
                startActivity(intent)
                finish()
            }, loadtime)

            binding.higherButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.swipeout_left))
            binding.listButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.disapear))
            binding.guessButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.disapear))
            binding.orderButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.disapear))
        }
    }

    /**
     *
     */
    @SuppressLint("ClickableViewAccessibility")
    private fun higherPressed() {

        binding.higherButton.setOnTouchListener(OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN ->
                    binding.higherButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_pressed))

                MotionEvent.ACTION_UP ->
                    binding.higherButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_relese))
            }
            false
        })
    }

    /**
     * Guess init.
     */
    private fun guessInit() {

        guessPressed()

        binding.guessButton.setOnClickListener() {

            binding.higherButton.isEnabled = false
            binding.listButton.isEnabled = false
            binding.orderButton.isEnabled = false
            binding.guessButton.isEnabled = false

            Handler().postDelayed(Runnable {
                val intent = Intent(this, GuessNumberActivity::class.java)
                startActivity(intent)
                finish()
            }, loadtime)

            binding.guessButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.swipeout_right))
            binding.higherButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.disapear))
            binding.listButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.disapear))
            binding.orderButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.disapear))
        }
    }

    /**
     *
     */
    @SuppressLint("ClickableViewAccessibility")
    private fun guessPressed() {

        binding.guessButton.setOnTouchListener(OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN ->
                    binding.guessButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_pressed))

                MotionEvent.ACTION_UP ->
                    binding.guessButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_relese))
            }
            false
        })
    }

    /**
     * Order init.
     */
    private fun orderInit() {

        orderPressed()

        binding.orderButton.setOnClickListener() {

            binding.higherButton.isEnabled = false
            binding.listButton.isEnabled = false
            binding.guessButton.isEnabled = false
            binding.orderButton.isEnabled = false

            Handler().postDelayed(Runnable {
                val intent = Intent(this, OrderNumberActivity::class.java)
                startActivity(intent)
                finish()
            }, loadtime)

            binding.orderButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.swipeout_left))
            binding.higherButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.disapear))
            binding.guessButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.disapear))
            binding.listButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.disapear))
        }
    }

    /**
     *
     */
    @SuppressLint("ClickableViewAccessibility")
    private fun orderPressed() {

        binding.orderButton.setOnTouchListener(OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN ->
                    binding.orderButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_pressed))

                MotionEvent.ACTION_UP ->
                    binding.orderButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_relese))
            }
            false
        })
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