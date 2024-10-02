package com.example.contadorvidasmagic2dammarioban

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.example.contadorvidasmagic2dammarioban.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    //PLAYER 1
    private lateinit var emptyHeartButton1: ImageButton
    private lateinit var countLifesTextPlayer1: TextView
    private lateinit var heartButtonPlayer1: ImageButton
    private lateinit var upArrowButton: ImageButton
    private lateinit var maxButtonPlayer1: Button
    private lateinit var minButtonPlayer1: Button

    //PLAYER 2
    private lateinit var emptyHeartButton2: ImageButton
    private lateinit var countLifesTextPlayer2: TextView
    private lateinit var heartButtonPlayer2: ImageButton
    private lateinit var downArrowButton: ImageButton
    private lateinit var maxButtonPlayer2: Button
    private lateinit var minButtonPlayer2: Button

    private var _binding: FragmentSecondBinding? = null

    private fun addLife(countLifesTextPlayer: TextView): TextView {
        val livesText = countLifesTextPlayer.text.toString().split("/")
        val leftLifeText = livesText[0].toInt() + 1

        val rightLifeText = livesText[1].toInt()

        val newTextValue = "${leftLifeText}/${rightLifeText}"
        return refresh(newTextValue, countLifesTextPlayer)
    }


    private fun addPoison(countLifesTextPlayer: TextView): TextView {
        var (leftLifeText, rightLifeText) = pair(countLifesTextPlayer)
        rightLifeText++
        val newTextValue = "${leftLifeText}/${rightLifeText}"
        return refresh(newTextValue, countLifesTextPlayer)
    }

    private fun pair(countLifesTextPlayer: TextView): Pair<Int, Int> {
        val livesText = countLifesTextPlayer.text.toString().split("/")
        val leftLifeText = livesText[0].toInt();

        val rightLifeText = livesText[1].toInt()
        return Pair(leftLifeText, rightLifeText)
    }

    private fun substractLife(countLifesTextPlayer: TextView): TextView {
        var (leftLifeText, rightLifeText) = pair(countLifesTextPlayer)
        leftLifeText--

        if (leftLifeText <= 0) {
            leftLifeText = 0
        }

        val newTextValue = "${leftLifeText}/${rightLifeText}"
        return refresh(newTextValue, countLifesTextPlayer)
    }

    private fun substractPoison(countLifesTextPlayer: TextView): TextView {
        var (leftLifeText, rightLifeText) = pair(countLifesTextPlayer)
        rightLifeText--


        if (rightLifeText <= 0) {
            rightLifeText = 0
        }

        val newTextValue = "${leftLifeText}/${rightLifeText}"

        return refresh(newTextValue, countLifesTextPlayer)
    }

    private fun refresh(newTextValue: String, countLifesTextPlayer: TextView): TextView {
        countLifesTextPlayer.text = newTextValue
        return countLifesTextPlayer
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //PLAYER 1
        countLifesTextPlayer1 = binding.CountLifesTextPlayer1
        emptyHeartButton1 = binding.EmptyHeartButtonPlayer1
        heartButtonPlayer1 = binding.HeartButtonPlayer1
        maxButtonPlayer1 = binding.button2
        minButtonPlayer1 = binding.button3


        //PLAYER 2
        emptyHeartButton2 = binding.HeartButtonPlayer2
        countLifesTextPlayer2 = binding.CountLifesTextPlayer2
        heartButtonPlayer2 = binding.EmptyHeartButtonPlayer2
        maxButtonPlayer2 = binding.button5
        minButtonPlayer2 = binding.button4

        upArrowButton = view.findViewById(R.id.UpArrowButton)
        downArrowButton = view.findViewById(R.id.DownArrowButton)


        // FUNCIONALITY PLAYER 1
        maxButtonPlayer1.setOnClickListener {
            addPoison(countLifesTextPlayer1)


        }
        minButtonPlayer1.setOnClickListener {
            substractPoison(countLifesTextPlayer1)
        }

        heartButtonPlayer1.setOnClickListener {
            addLife(countLifesTextPlayer1)
        }
        emptyHeartButton1.setOnClickListener {

            substractLife(countLifesTextPlayer1)

        }
        // FUNCIONALITY PLAYER 2
        maxButtonPlayer2.setOnClickListener {
            addPoison(countLifesTextPlayer2)


        }
        minButtonPlayer2.setOnClickListener {
            substractPoison(countLifesTextPlayer2)
        }


        heartButtonPlayer2.setOnClickListener {
            substractLife(countLifesTextPlayer2)
        }
        emptyHeartButton2.setOnClickListener {

            addLife(countLifesTextPlayer2)

        }

        // TRADE LIFE FUNCIONALITY
        upArrowButton.setOnClickListener {


            val (leftLifeText, leftLifeText2) = pairLeft()

            if (leftLifeText > 0 && leftLifeText2 > 0) {
                addLife(countLifesTextPlayer1)
                substractLife(countLifesTextPlayer2)
            } else if (leftLifeText == 0 && leftLifeText2 > 0) {
                substractLife(countLifesTextPlayer2)
                addLife(countLifesTextPlayer1)
            }

        }
        downArrowButton.setOnClickListener {
            val (leftLifeText, leftLifeText2) = pairLeft()

            if (leftLifeText > 0 && leftLifeText2 > 0) {
                addLife(countLifesTextPlayer2)
                substractLife(countLifesTextPlayer1)
            } else if (leftLifeText > 0 && leftLifeText2 == 0) {
                substractLife(countLifesTextPlayer1)
                addLife(countLifesTextPlayer2)
            }
        }


    }

    private fun pairLeft(): Pair<Int, Int> {
        val livesText = countLifesTextPlayer1.text.toString().split("/")
        val livesText2 = countLifesTextPlayer2.text.toString().split("/")

        val leftLifeText = livesText[0].toInt()
        val leftLifeText2 = livesText2[0].toInt()
        return Pair(leftLifeText, leftLifeText2)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}