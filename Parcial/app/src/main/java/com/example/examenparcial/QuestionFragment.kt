package com.example.examenparcial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class QuestionFragment : Fragment() {

    private lateinit var option1Button: Button
    private lateinit var option2Button: Button
    private lateinit var option3Button: Button
    private lateinit var option4Button: Button
    private lateinit var questionImageView: ImageView

    private var score = 0
    private var currentIndex = 0
    private val questions = listOf(
        "¿Cuál es la capital de Francia?" to "París",
        "¿En qué año comenzó la Segunda Guerra Mundial?" to "1939",
        "¿Quién escribió 'Don Quijote de la Mancha'?" to "Miguel de Cervantes",
        "¿En qué año se descubrió América?" to "1492"
    )

    private val answers = listOf(
        "París", "Coruña", "Peru", "Berlín",
        "1939", "1940", "1941", "1945",
        "Miguel de Cervantes", "William Shakespeare", "Garcilaso de la Vega", "Lope de Vega",
        "1492", "1493", "1494", "1496"
    )

    private val questionImages = listOf(
        R.drawable.paris_image,
        R.drawable.ww2_image,
        R.drawable.quijote_image,
        R.drawable.america_image,
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_question, container, false)

        // Asociar botones
        option1Button = view.findViewById(R.id.option1_button)
        option2Button = view.findViewById(R.id.option2_button)
        option3Button = view.findViewById(R.id.option3_button)
        option4Button = view.findViewById(R.id.option4_button)
        questionImageView = view.findViewById(R.id.question_image)

        // Mostrar la primera pregunta
        showQuestion()

        // Manejar clics en los botones
        option1Button.setOnClickListener {
            checkAnswer(option1Button.text.toString())
        }

        option2Button.setOnClickListener {
            checkAnswer(option2Button.text.toString())
        }

        option3Button.setOnClickListener {
            checkAnswer(option3Button.text.toString())
        }

        option4Button.setOnClickListener {
            checkAnswer(option4Button.text.toString())
        }

        return view
    }

    private fun showQuestion() {
        // Verificar si hay más preguntas por mostrar
        if (currentIndex < questions.size) {
            val currentQuestion = questions[currentIndex]
            val currentAnswers = answers.subList(currentIndex * 4, (currentIndex + 1) * 4)
            val currentImage = questionImages[currentIndex]

            // Mostrar la pregunta actual
            view?.findViewById<TextView>(R.id.question_text)?.text = currentQuestion.first

            // Mostrar las respuestas
            option1Button.text = currentAnswers[0]
            option2Button.text = currentAnswers[1]
            option3Button.text = currentAnswers[2]
            option4Button.text = currentAnswers[3]

            // Mostrar la imagen correspondiente
            questionImageView.setImageResource(currentImage)
        }
    }

    private fun checkAnswer(selectedAnswer: String) {
        val correctAnswer = questions[currentIndex].second // Obtenemos la respuesta correcta

        // Verificar si la respuesta seleccionada es correcta
        val isCorrect = selectedAnswer.equals(correctAnswer, ignoreCase = true)

        // Mostrar retroalimentación al usuario
        if (isCorrect) {
            Toast.makeText(requireActivity(), "Respuesta correcta! +25 puntos", Toast.LENGTH_SHORT).show()
            score += 25 // Sumar 25 puntos por respuesta correcta
        } else {
            Toast.makeText(requireActivity(), "Aww Respuesta incorrecta! -25 puntos", Toast.LENGTH_SHORT).show()
            score -= 25 // Restar 25 puntos por respuesta incorrecta
        }

        // Incrementar el índice actual solo si hay más preguntas disponibles
        if (currentIndex < questions.size - 1) {
            currentIndex++
            // Mostrar la siguiente pregunta
            showQuestion()
        } else {
            // Mostrar el puntaje cuando se han respondido todas las preguntas
            showScore()
        }
    }

    private fun showScore() {
        // Mostrar el puntaje al usuario, por ejemplo, en un nuevo fragmento
        val scoreFragment = ScoreFragment()
        val bundle = Bundle()
        bundle.putInt("score", score)
        scoreFragment.arguments = bundle
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, scoreFragment)
            .commit()
    }
}