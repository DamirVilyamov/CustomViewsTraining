package com.example.customviewstraining


import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout


class CustomView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    private var numOfButtons: Int = 3
    private val TAG = "CustomView"
    var mainButton: Button
    var button1: Button
    var button2: Button
    var button3: Button
    var button4: Button
    var button5: Button
    var activeButtonsList = ArrayList<Button>()

    init {
        initializeViews(context)
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView)
        numOfButtons = typedArray.getInt(R.styleable.CustomView_numberOfSmallButtons, 3)
        typedArray.recycle()

        mainButton = this.findViewById<Button>(R.id.main_button)
        button1 = this.findViewById<Button>(R.id.button1)
        button2 = this.findViewById<Button>(R.id.button2)
        button3 = this.findViewById<Button>(R.id.button3)
        button4 = this.findViewById<Button>(R.id.button4)
        button5 = this.findViewById<Button>(R.id.button5)

        when (numOfButtons) {
            3 -> {
                activeButtonsList.add(button1)
                activeButtonsList.add(button3)
                activeButtonsList.add(button5)
            }
            4 -> {
                activeButtonsList.add(button1)
                activeButtonsList.add(button2)
                activeButtonsList.add(button4)
                activeButtonsList.add(button5)
            }
            5 -> {
                activeButtonsList.add(button1)
                activeButtonsList.add(button2)
                activeButtonsList.add(button3)
                activeButtonsList.add(button4)
                activeButtonsList.add(button5)
            }
        }
    }


    private fun initializeViews(context: Context) {
        val inflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.custom_view, this)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        mainButton.setOnLongClickListener(View.OnLongClickListener {
            Log.d(TAG, "longclick: ")
            var latency = 0L
            for (button in activeButtonsList) {
                latency += 200L
                button.postDelayed({
                    button.visibility = View.VISIBLE
                }, latency)
            }
            latency = 0
            return@OnLongClickListener true
        })
        mainButton.setOnClickListener {
            var latency = 0L
            for (button in activeButtonsList.asReversed()) {
                latency += 200L
                button.postDelayed({
                    button.visibility = View.INVISIBLE
                }, latency)
            }
            latency = 0
        }
    }
}