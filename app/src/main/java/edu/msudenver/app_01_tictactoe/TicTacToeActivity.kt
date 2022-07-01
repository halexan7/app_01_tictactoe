package edu.msudenver.tictactoe

/*
 * CS3013 - Mobile App Dev. - Summer 2022
 * Instructor: Thyago Mota
 * Student(s): Horace Alexander
 * Description: App 01 - TicTacToe Activity class
 */

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import edu.msudenver.app_01_tictactoe.R

class TicTacToeActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var playerInfo: TextView
    private lateinit var gridLayout: GridLayout



    // TODOd (suggested): maintain a reference to a TicTacToe object
    var game: TicTacToe ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tictactoe)

        // TODOd (suggested): get the player's name, symbol, and the first move choice from the activity's intent;
        //  use them to instantiate a TicTacToe game; decide whether the computer should play based on the first move choice
        val playerName = intent.getStringExtra("name")
        val symbol = intent.getCharExtra("symbol",'?')
        val firstMove = intent.getBooleanExtra("first move",false)
        game = TicTacToe(playerName.toString(), symbol)

        // TODOd (suggested): get a reference to the TextView "player info" area;
        //  update the TextView with the player's name and symbol
        playerInfo = findViewById(R.id.playerInfo)
        playerInfo.text = "Player $playerName is using $symbol"


        // TODOd (suggested): using a loop and button tags, update their texts and "onClick" listeners to TicTacToeActivity;
        //  remember to disable the button if it corresponds to a computer's first move
        // hint: use "findViewWithTag"
        gridLayout = findViewById(R.id.gridLayout)
        for (i in 0 until 3){
            for (j in 0 until 3){
                val tag: String = "$i,$j"
                val button: Button = gridLayout.findViewWithTag(tag)
                button.setOnClickListener(this)
            }
        }
        if (!firstMove){
            game?.computerPlay()
            val last = game?.lastMove
            val i = last?.first?.toInt()
            val j = last?.second?.toInt()
            val button: Button = gridLayout.findViewWithTag("$i,$j")
            button.isEnabled = false
            button.text = game?.getComputerSymbol().toString()
        }
    }

    // TODOd (suggested): display a Toast with a text based on the game's result
    fun showResults() {
        if (game?.isGameOver() == true){
            val win = game?.getWinner()
            if (win == game?.getComputerSymbol()){
                Toast.makeText(this, "I won; computers are superior!!!", Toast.LENGTH_LONG).show()
            }
            else if (win == game?.playerSymbol){
                Toast.makeText(this, "You won; congratulations!!!", Toast.LENGTH_LONG).show()
            }
            else {
                Toast.makeText(this, "Tie!!!", Toast.LENGTH_LONG).show()
            }
        }
    }

    // TODOd (suggested): cast the given view as a Button;
    //  disable the button so you don't forget;
    //  get the button's tag and use it to infer the player's move coordinates;
    //  make the move and update the button's text with the player's symbol;
    //  if the game is over, show results;
    //  otherwise, have the computer play;
    //  use TitTacToe's last move and "findViewWithTag" to get a reference to the button of the computer's play;
    //  disable the button so you don't forget;
    //  update the button's text with the computer's symbol;
    //  if the game is over, show results
    override fun onClick(view: View?) {
        val button = view as Button
        button.isEnabled = false
        val tag = button.tag.toString()
        val arr = tag.split(",")
        game?.playerPlay(arr[0].toInt(), arr[1].toInt())
        button.text = intent.getCharExtra("symbol",'?').toString()
        if (game?.isGameOver() == true){
            showResults()
        }
        else {
            game?.computerPlay()
            val last = game?.lastMove
            val i = last?.first?.toInt()
            val j = last?.second?.toInt()
            val button: Button = gridLayout.findViewWithTag("$i,$j")
            button.isEnabled = false
            button.text = game?.getComputerSymbol().toString()
            if (game?.isGameOver() == true){
                showResults()
            }
        }
    }


    // TODOd: save the TicTacToe object using the outState Bundle
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("game",game)
    }

    // TODOd: restore the TicTacToe object using the savedInstanceState Bundle
    override fun onRestoreInstanceState(savedIntanceState: Bundle) {
        super.onRestoreInstanceState(savedIntanceState)
        game = savedIntanceState.getSerializable("game") as TicTacToe?
    }
}