package com.example.newengland.UserPages.UserPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.example.newengland.R
import com.example.newengland.databinding.ActivityClubBinding
import java.util.*

class Club : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    lateinit var Club: String
    lateinit var Club2: String
    private lateinit var binding: ActivityClubBinding
        override fun onCreate(savedInstanceState: Bundle?) {
            val actionBar = supportActionBar
            if(actionBar != null)
            {
                actionBar.title="RamdomResults"
            }
            actionBar?.setDisplayHomeAsUpEnabled(true)
            super.onCreate(savedInstanceState)
            binding = ActivityClubBinding.inflate(layoutInflater)
            setContentView(binding.root)

            val spinner1: Spinner = findViewById(R.id.spinner1)

            ArrayAdapter.createFromResource(
                this,
                R.array.Clubs,
                android.R.layout.simple_spinner_item
            ).also { adapter ->


                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                spinner1.adapter = adapter
                spinner1.onItemSelectedListener = this

                spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long
                    ) {
                        val selectedItem = parent.getItemAtPosition(position).toString()
                        if(selectedItem== "Miami Dolphins")
                        {
                            Club= "Miami Dolphins";
                        }
                        else if(selectedItem == "New England Patriots")
                        {
                            Club = "New England Patriots"
                        }
                        else if(selectedItem == "Buffalo Bills")
                        {
                            Club = "Buffalo Bills"
                        }
                        else if(selectedItem == "New York Jets")
                        {
                            Club = "New York Jets"
                        }
                        else if(selectedItem == "New York Giants")
                        {
                            Club2 = "New York Giants"
                        }
                        else if(selectedItem == "Minnesota Vikings")
                        {
                            Club2 = "Minnesota Vikings"

                        }

                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                }
                val spinner2: Spinner = findViewById(R.id.spinner2)

                ArrayAdapter.createFromResource(
                    this,
                    R.array.Clubs,
                    android.R.layout.simple_spinner_item
                ).also { adapter ->

                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                    spinner2.adapter = adapter
                    spinner2.onItemSelectedListener = this
                    spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>,
                            view: View,
                            position: Int,
                            id: Long
                        ) {
                            val selectedItem = parent.getItemAtPosition(position).toString()
                            if(selectedItem== "Miami Dolphins")
                            {
                                Club2= "Miami Dolphins";
                            }
                            else if(selectedItem == "New England Patriots")
                            {
                                Club2 = "New England Patriots"
                            }
                            else if(selectedItem == "Buffalo Bills")
                            {
                                Club2 = "Buffalo Bills"
                            }
                            else if(selectedItem == "New York Jets")
                            {
                                Club2 = "New York Jets"
                            }
                            else if(selectedItem == "New York Giants")
                            {
                                Club2 = "New York Giants"
                            }
                            else if(selectedItem == "Minnesota Vikings")
                            {
                                Club2 = "Minnesota Vikings"
                            }

                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {}
                    }
                binding.ErgebnisBttn.setOnClickListener()
                {

                    binding.resultsTextView.setText("")
                     val teams = listOf(Club,Club2 )
                     val scores = mutableMapOf<String, Int>()
                    teams.forEach { scores[it] = 0 }

                    val random = Random()

                    val resultsTextView = findViewById<TextView>(R.id.results_text_view)
                    while (scores.values.any { it < 2 }) {
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


        }


        }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}

