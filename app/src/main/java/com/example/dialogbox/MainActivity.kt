package com.example.dialogbox

import android.graphics.Color
import android.os.Bundle
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

        chooseColorAlert.setCancelable(false)
            .setTitle("Choose your favorite color")
            .setSingleChoiceItems(colors,-1) { _, which->
                when(which){
                    0->showColorTextView.setTextColor(Color.RED)
                    1->showColorTextView.setTextColor(Color.BLUE)
                    2->showColorTextView.setTextColor(Color.GREEN)
                    3->showColorTextView.setTextColor(Color.YELLOW)
                }
                showColorTextView.text = colors[which]
            }
            .setPositiveButton("Ok") { dialog, _ ->
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