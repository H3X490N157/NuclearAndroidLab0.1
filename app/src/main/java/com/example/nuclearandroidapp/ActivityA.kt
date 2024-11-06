package com.example.nuclearandroidapp

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager



class ActivityA : AppCompatActivity() {
    private val fragmentBb = "FRAGMENT_BB"
    private val fragmentBa = "FRAGMENT_BA"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Log.d("ActivityB", "Task_id: $taskId")
        setContentView(R.layout.activity_a)
    }

    private fun openOrReuseFragmentBB() {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentBB: FragmentBB? =
            fragmentManager.findFragmentByTag(fragmentBb) as? FragmentBB

        if (fragmentBB == null) {
            Log.d("сообщение", "Создание нового FragmentBB")
            val newFragmentBB = FragmentBB()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, newFragmentBB, fragmentBb)
                .addToBackStack(null)
                .commit()
        } else {
            Log.d("сообщение", "Использование существующего FragmentBB")
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragmentBB, fragmentBb)
                .addToBackStack(null)
                .commit()
        }
    }

    private fun openFragmentsForLandscape() {
        val fragmentManager = supportFragmentManager

        // Открываем FragmentBB в контейнере fragment_container_bb
        var fragmentBB = fragmentManager.findFragmentByTag(fragmentBb) as? FragmentBB
        if (fragmentBB == null) {
            fragmentBB = FragmentBB()
            fragmentManager.beginTransaction()
                .replace(R.id.fragment_container_bb, fragmentBB, fragmentBb)
                .commit()
        }

        // Открываем FragmentBA в контейнере fragment_container_ba
        var fragmentBA = fragmentManager.findFragmentByTag(fragmentBa) as? FragmentBA
        if (fragmentBA == null) {
            fragmentBA = FragmentBA()
            fragmentManager.beginTransaction()
                .replace(R.id.fragment_container_ba, fragmentBA, fragmentBa)
                .commit()
        }
    }

    override fun onStart() {
        super.onStart()
        val buttonOpenActivityB: Button = findViewById(R.id.button_open_activity_b)
        val buttonOpenFragmentB: Button = findViewById(R.id.button_open_fragment_b)

        // Открытие новой активности
        buttonOpenActivityB.setOnClickListener {
            val intent = Intent(this, ActivityB::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
            startActivity(intent)
        }
        // Открытие фрагмента B
        buttonOpenFragmentB.setOnClickListener {
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                openOrReuseFragmentBB()
            } else {
                openFragmentsForLandscape()
            }
        }
    }


    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.d("ActivityA", "OnNewIntent")
        Log.d("ActivityA", "Task_id: $taskId")

    }
}

