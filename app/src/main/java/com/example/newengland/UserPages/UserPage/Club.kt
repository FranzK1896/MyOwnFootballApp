package com.example.newengland.UserPages.UserPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.newengland.R
import java.util.*

class Club : AppCompatActivity() {


        override fun onCreate(savedInstanceState: Bundle?) {
            val actionBar = supportActionBar
            if(actionBar != null)
            {
                actionBar.title="RamdomResults"
            }
            actionBar?.setDisplayHomeAsUpEnabled(true)
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_club)

            val teams = listOf("New England Patriots", "New York Giants", "Dallas Cowboys", "Green Bay Packers", "Pittsburgh Steelers")
            val scores = mutableMapOf<String, Int>()
            teams.forEach { scores[it] = 0 }

            val random = Random()

            val resultsTextView = findViewById<TextView>(R.id.results_text_view)

            while (scores.values.any { it < 3 }) {
                val homeTeam = teams[random.nextInt(teams.size)]
                val awayTeam = teams[random.nextInt(teams.size)]
                if (homeTeam == awayTeam) continue

                val homeScore = random.nextInt(50)
                val awayScore = random.nextInt(50)

                val result = "$homeTeam $homeScore - $awayScore $awayTeam\n"
                resultsTextView.append(result)
                scores[homeTeam] = scores[homeTeam]!! + if (homeScore > awayScore) 1 else 0
                scores[awayTeam] = scores[awayTeam]!! + if (awayScore > homeScore) 1 else 0
            }

            scores.toList().sortedByDescending { (_, value) -> value }.forEach { (team, score) ->
                val standing = "$team: $score\n"
                resultsTextView.append(standing)
            }
        }
    }
