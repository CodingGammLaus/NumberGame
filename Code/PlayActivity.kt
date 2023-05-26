package se.umu.cs.dv21sln.numbergame

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import se.umu.cs.dv21sln.numbergame.databinding.PlayActivityBinding

class PlayActivity : AppCompatActivity() {

    private lateinit var binding: PlayActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*Get the view binding*/
        binding = PlayActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listInit()
        higherInit()
        guessInit()
    }

    /**
     * List init.
     */
    private fun listInit() {

        binding.listButton.setOnClickListener() {

            val intent = Intent(this, List5Activity::class.java)
            startActivity(intent)
            finish()
        }
    }

    /**
     * Higher init.
     */
    private fun higherInit() {

        binding.higherButton.setOnClickListener() {

            val intent = Intent(this, HigherLowerActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    /**
     * Guess init.
     */
    private fun guessInit() {

        binding.guessButton.setOnClickListener() {

            val intent = Intent(this, GuessNumberActivity::class.java)
            startActivity(intent)
            finish()
        }
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