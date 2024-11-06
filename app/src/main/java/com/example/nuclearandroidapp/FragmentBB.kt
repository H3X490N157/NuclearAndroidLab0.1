package com.example.nuclearandroidapp

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment


class FragmentBB : Fragment() {
    private val fragmentBa = "FRAGMENT_BA"
    private val bgColor = "bgColor"
    private val colorKey = "colorKey"
    private val colorRequestKey = "colorRequestKey"
    private var backgroundColor: Int = Color.WHITE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            backgroundColor = savedInstanceState.getInt(bgColor)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bb, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackgroundColor(backgroundColor)

        val buttonOpenFragmentBA: Button = view.findViewById(R.id.button_open_fragment_ba)
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Скрываем кнопку, так как FragmentBA уже открыт
            buttonOpenFragmentBA.visibility = View.GONE
        } else {
            // Показать кнопку в портретной ориентации
            buttonOpenFragmentBA.visibility = View.VISIBLE
            buttonOpenFragmentBA.setOnClickListener {

                val fragmentBA: FragmentBA? =
                    parentFragmentManager.findFragmentByTag(fragmentBa) as? FragmentBA

                if (fragmentBA == null) {
                    val newFragmentBA = FragmentBA()
                    parentFragmentManager.beginTransaction()
                        .add(R.id.fragment_container, newFragmentBA, fragmentBa)
                        .addToBackStack(null)
                        .commit()
                } else {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragmentBA, fragmentBa)
                        .addToBackStack(null)
                        .commit()
                }
            }
        }
        parentFragmentManager.setFragmentResultListener(colorRequestKey, this) { _, bundle ->
            val color = bundle.getInt(colorKey)
            view.setBackgroundColor(color)
            backgroundColor = color // Устанавливаем цвет
        }
    }

    // Метод для изменения фонового цвета
    private fun setBackgroundColor(color: Int) {
        backgroundColor = color
        view?.setBackgroundColor(backgroundColor)
    }
}