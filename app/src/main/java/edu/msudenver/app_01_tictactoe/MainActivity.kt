package edu.msudenver.tictactoe

/*
 * CS3013 - Mobile App Dev. - Summer 2022
 * Instructor: Thyago Mota
 * Student(s): Horace Alexander
 * Description: App 01 - MainActivity class
 */

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import edu.msudenver.app_01_tictactoe.R

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var playButton: Button
    private lateinit var playerName: EditText
    private lateinit var noughts: RadioButton
    private lateinit var crosses: RadioButton
    private lateinit var first: RadioButton
    private lateinit var notFirst: RadioButton
    private lateinit var playerInfo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODOd (suggested): get a reference to the "play" button and use it to set its "onClick" listener to MainActivity
        playButton = findViewById(R.id.playButton)
        playButton.setOnClickListener(this)
    }

    // TODOd (suggested): get the player's name, symbol, and the first move choice;
    //  pass info to the TicTacToe activity
    //explicit intent
    //put extra passes info
    override fun onClick(p0: View?) {
        playerName = findViewById(R.id.playerName)
        val name = playerName.text.toString()

        noughts = findViewById(R.id.noughts)
        crosses = findViewById(R.id.crosses)
        val symbol = if (noughts.isChecked) 'O' else 'X'

        first = findViewById(R.id.first)
        notFirst = findViewById(R.id.notFirst)
        val firstMove = first.isChecked

        val intent = Intent(this, TicTacToeActivity::class.java)
        intent.putExtra("name", name)
        intent.putExtra("symbol", symbol)
        intent.putExtra("first move", firstMove)
        startActivity(intent)
    }
}