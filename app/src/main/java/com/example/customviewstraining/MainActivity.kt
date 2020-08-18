package com.example.customviewstraining

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = "!@#"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main_button.setOnLongClickListener(View.OnLongClickListener {
            Log.d(TAG, "longclick: ")
            button.postDelayed({
                Log.d(TAG, "onCreate: postdelayed called")
                button.visibility = View.VISIBLE
            }, 200)
            button2.postDelayed({
                button2.visibility = View.VISIBLE
            }, 400)
            button3.postDelayed({
                button3.visibility = View.VISIBLE
            }, 600)
            return@OnLongClickListener true
        })

        main_button.setOnClickListener {
            button.postDelayed({
                Log.d(TAG, "onCreate: postdelayed called")
                button.visibility = View.INVISIBLE
            }, 600)
            button2.postDelayed({
                button2.visibility = View.INVISIBLE
            }, 400)
            button3.postDelayed({
                button3.visibility = View.INVISIBLE
            }, 200)
        }

        button.setOnClickListener {
           nextColor(it)
        }
        button2.setOnClickListener {
            nextColor(it)
        }
        button3.setOnClickListener {
            nextColor(it)
        }



    }

    private fun nextColor(button: View){
        when(button.background){
            ContextCompat.getDrawable(this, R.drawable.round_button_red) -> {
                button.background = ContextCompat.getDrawable(this, R.drawable.round_button_yellow)
            }
            ContextCompat.getDrawable(this, R.drawable.round_button_yellow) -> {
                button.background = ContextCompat.getDrawable(this, R.drawable.round_button_green)
            }
            ContextCompat.getDrawable(this, R.drawable.round_button_green) -> {
                button.background = ContextCompat.getDrawable(this, R.drawable.round_button_red)
            }
        }
    }

}