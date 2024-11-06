package com.example.nuclearandroidapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActivityB : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ActivityB", "Task_id: $taskId")
        enableEdgeToEdge()
        setContentView(R.layout.activity_b)
        val buttonActC: Button = findViewById(R.id.button_open_activity_c)
        buttonActC.setOnClickListener{
            val intent = Intent(this, ActivityC::class.java)
            startActivity(intent)
        }
    }
}