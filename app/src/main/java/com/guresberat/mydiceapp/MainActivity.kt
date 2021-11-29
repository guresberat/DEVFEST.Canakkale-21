package com.guresberat.mydiceapp

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var diceImage: ImageView

    class Dice(private val numSides: Int) {
        fun roll(): Int {
            return (1..numSides).random()
        }
    }

    private fun rollDice() {
        val dice = Dice(6)
        val drawableResource = when (dice.roll()) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        diceImage.setImageResource(drawableResource)
    }

    private fun rotateDiceWithAnim() {
        val rotateAnim: Animation = AnimationUtils.loadAnimation(this, R.anim.rotate)
        rotateAnim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
            }

            //this metod is used for timing the dice image change with the animation.
            //without this,dice image changes when the button is clicked,then the animation starts
            override fun onAnimationEnd(p0: Animation?) {
                rollDice()
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }

        })
        diceImage.startAnimation(rotateAnim)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.button)
        diceImage = findViewById(R.id.imageView)

        rollButton.setOnClickListener {
            rotateDiceWithAnim()
        }
        rollDice()
    }
}