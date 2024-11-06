package com.example.nuclearandroidapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActivityC : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ActivityC", "Task_id: $taskId")
        enableEdgeToEdge()
        setContentView(R.layout.activity_c)
        val buttonActC: Button = findViewById(R.id.button_open_activity_a)
        buttonActC.setOnClickListener{
            val intent = Intent(this, ActivityA::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            startActivity(intent)
        }
    }
}