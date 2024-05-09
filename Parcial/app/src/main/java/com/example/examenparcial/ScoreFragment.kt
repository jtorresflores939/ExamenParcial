package com.example.examenparcial

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ScoreFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_score, container, false)

        // Obtener el puntaje del argumento
        val score = arguments?.getInt("score") ?: 0

        // Mostrar el puntaje en un TextView
        val scoreTextView: TextView = view.findViewById(R.id.score_text)
        scoreTextView.text = "Puntaje final: $score"

        return view
    }
}