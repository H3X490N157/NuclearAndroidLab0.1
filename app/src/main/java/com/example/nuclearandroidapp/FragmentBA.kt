package com.example.nuclearandroidapp

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import kotlin.random.Random

class FragmentBA : Fragment() {
    private val fragmentBb = "FRAGMENT_BB"
    private val colorKey = "colorKey"
    private val colorRequestKey = "colorRequestKey"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ba, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonSetColor: Button = view.findViewById(R.id.button_set_color)
        buttonSetColor.setOnClickListener {
            val randomColor = getRandomColor()
            val resultBundle = Bundle().apply {
                putInt(colorKey, randomColor) // Сохраняем цвет в бандл
            }
            parentFragmentManager.setFragmentResult(colorRequestKey, resultBundle) // Отправляем результат
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                val fragmentBB = parentFragmentManager.findFragmentByTag(fragmentBb)
                if (fragmentBB != null) {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragmentBB)
                        .addToBackStack(null)
                        .commit()
                }
            }
        }
    }

    private fun getRandomColor(): Int {
        val r = Random.nextInt(256)
        val g = Random.nextInt(256)
        val b = Random.nextInt(256)
        return Color.rgb(r, g, b)
    }
}
