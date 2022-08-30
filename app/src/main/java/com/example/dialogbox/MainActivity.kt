package com.example.dialogbox

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chooseColorDialogBox()

        val chooseColorBtn : Button = findViewById(R.id.chooseColorBtn)

        chooseColorBtn.setOnClickListener{
            chooseColorDialogBox()
        }

    }

    private fun chooseColorDialogBox(){
        val chooseColorAlert = AlertDialog.Builder(this)
        val showColorTextView = findViewById<TextView>(R.id.showColor)
        val colors = arrayOf("RED","BLUE","GREEN","YELLOW")
        val arrOfSelected = arrayListOf<Int>()
        var color = SpannableStringBuilder("")

        chooseColorAlert.setCancelable(false)
            .setTitle("Choose your favorite color")
            .setMultiChoiceItems(colors,null) { _, which, checked ->
                if(checked){
                    arrOfSelected.add(which)
                } else if(arrOfSelected.contains(which)) {
                    arrOfSelected.remove(which)
                }
            }
            .setPositiveButton("Ok") { dialog, _ ->
                for(element in arrOfSelected) {
                    val color2 = SpannableString(colors[element]+"")
                    when(element){
                        0->{
                            val fColor = ForegroundColorSpan(Color.RED)
                            color2.setSpan(fColor,0, color2.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
                        }
                        1->{
                            val fColor = ForegroundColorSpan(Color.BLUE)
                            color2.setSpan(fColor,0, color2.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
                        }
                        2->{
                            val fColor = ForegroundColorSpan(Color.GREEN)
                            color2.setSpan(fColor,0, color2.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
                        }
                        3->{
                            val fColor = ForegroundColorSpan(Color.YELLOW)
                            color2.setSpan(fColor,0, color2.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
                        }
                    }
                    color.append(color2)
                }
                showColorTextView.text = color
                dialog.dismiss()
            }
            .create()
            .show()
    }

    override fun onBackPressed() {
        val exitDialog = AlertDialog.Builder(this)
        exitDialog.setCancelable(false)
        exitDialog.setIcon(R.drawable.ic_baseline_exit_to_app_24)
            .setTitle("Exit")
            .setMessage("Do you really want to exit?")
            .setPositiveButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .setNegativeButton("Yes") { _, _ ->
                super.onBackPressed()
            }
            .create()
            .show()
    }

}