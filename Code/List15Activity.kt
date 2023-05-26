package se.umu.cs.dv21sln.numbergame

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import se.umu.cs.dv21sln.numbergame.databinding.List15ActivityBinding

class List15Activity : AppCompatActivity() {

    private lateinit var binding: List15ActivityBinding

    private lateinit var adapterItem: ArrayAdapter<String>
    val menuItemList = arrayListOf<String>("List 5", "List 10")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*Get the view binding*/
        binding = List15ActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpMenu()

        quitButtonInit()
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
    private fun quitButtonInit() {

        binding.quitButton.setOnClickListener() {

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

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
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