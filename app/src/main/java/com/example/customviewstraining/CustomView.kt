package com.example.customviewstraining


import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat


class CustomView : ConstraintLayout {

    constructor(context: Context) : super(context) {
        initializeViews(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initializeViews(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initializeViews(context, attrs, defStyleAttr)
    }

    private val defButtonBackGreen: Drawable? =
        ContextCompat.getDrawable(context, R.drawable.round_button_green)
    private val defButtonBackRed: Drawable? =
        ContextCompat.getDrawable(context, R.drawable.round_button_red)
    private val defButtonBackYellow: Drawable? =
        ContextCompat.getDrawable(context, R.drawable.round_button_yellow)

    private var numOfButtons: Int = 3
    private val TAG = "CustomView"
    lateinit var mainButton: Button
    lateinit var button1: Button
    lateinit var button2: Button
    lateinit var button3: Button
    lateinit var button4: Button
    lateinit var button5: Button
    var button1BackgroundRes: Drawable? = defButtonBackGreen
    var button2BackgroundRes: Drawable? = defButtonBackGreen
    var button3BackgroundRes: Drawable? = defButtonBackRed
    var button4BackgroundRes: Drawable? = defButtonBackYellow
    var button5BackgroundRes: Drawable? = defButtonBackYellow
    var activeButtonsList = ArrayList<Button>()
    private var areButtonsActive = false


    private fun initializeViews(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int? = null
    ) {

        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView)
        numOfButtons = typedArray.getInt(R.styleable.CustomView_numberOfSmallButtons, 3)
        button1BackgroundRes =
            typedArray.getDrawable(R.styleable.CustomView_button1Background)
        button2BackgroundRes =
            typedArray.getDrawable(R.styleable.CustomView_button2Background)
        button3BackgroundRes =
            typedArray.getDrawable(R.styleable.CustomView_button3Background)
        button4BackgroundRes =
            typedArray.getDrawable(R.styleable.CustomView_button4Background)
        button5BackgroundRes =
            typedArray.getDrawable(R.styleable.CustomView_button5Background)
        typedArray.recycle()

        val inflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.custom_view, this)

        mainButton = this.findViewById(R.id.main_button)
        button1 = this.findViewById(R.id.button1)
        button2 = this.findViewById(R.id.button2)
        button3 = this.findViewById(R.id.button3)
        button4 = this.findViewById(R.id.button4)
        button5 = this.findViewById(R.id.button5)


        when (numOfButtons) {
            3 -> {
                with(activeButtonsList) {
                    button1.background = button1BackgroundRes
                    add(button1)
                    button3.background = button3BackgroundRes
                    add(button3)
                    button5.background = button5BackgroundRes
                    add(button5)
                }
            }
            4 -> {
                with(activeButtonsList) {
                    button1.background = button1BackgroundRes
                    add(button1)
                    button2.background = button2BackgroundRes
                    add(button2)
                    button4.background = button4BackgroundRes
                    add(button4)
                    button5.background = button5BackgroundRes
                    add(button5)
                }
            }
            5 -> {
                with(activeButtonsList) {
                    button1.background = button1BackgroundRes
                    add(button1)
                    button2.background = button2BackgroundRes
                    add(button2)
                    button3.background = button3BackgroundRes
                    add(button3)
                    button4.background = button4BackgroundRes
                    add(button4)
                    button5.background = button5BackgroundRes
                    add(button5)
                }
            }
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        mainButton.setOnLongClickListener(OnLongClickListener {
            if (!areButtonsActive) {
                Log.d(TAG, "longclick: ")
                var latency = 0L
                for (button in activeButtonsList) {
                    button.setOnClickListener { nextColor(button) }
                    latency += 200L
                    button.postDelayed({
                        button.visibility = View.VISIBLE
                    }, latency)
                }
                areButtonsActive = true
            } else if (areButtonsActive) {
                var latency = 0L
                for (button in activeButtonsList.asReversed()) {
                    latency += 200L
                    button.postDelayed({
                        button.visibility = View.INVISIBLE
                    }, latency)
                }
                areButtonsActive = false
            }
            return@OnLongClickListener true
        })


    }

    private fun nextColor(button: Button) {
        Log.d(TAG, "nextColor: called")
        when (button.background) {
            ContextCompat.getDrawable(context, R.drawable.round_button_red) -> {
                button.background =
                    ContextCompat.getDrawable(context, R.drawable.round_button_yellow)
            }
            ContextCompat.getDrawable(context, R.drawable.round_button_yellow) -> {
                button.background =
                    ContextCompat.getDrawable(context, R.drawable.round_button_green)
            }
            ContextCompat.getDrawable(context, R.drawable.round_button_green) -> {
                button.background = ContextCompat.getDrawable(context, R.drawable.round_button_red)
            }
        }
    }
}