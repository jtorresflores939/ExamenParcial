package com.example.examenparcial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.Button

class WelcomeFragment : Fragment() {

    private lateinit var startGameButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_welcome, container, false)

        // Asociar botón de inicio de juego
        startGameButton = view.findViewById(R.id.start_game_button)

        // Manejar clic en el botón de inicio de juego
        startGameButton.setOnClickListener {
            // Navegar al fragmento de preguntas cuando se hace clic en el botón
            val questionFragment = QuestionFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, questionFragment)
                .addToBackStack(null)
                .commit()
        }

        return view
    }
}
