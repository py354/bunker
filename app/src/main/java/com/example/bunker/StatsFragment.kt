package com.example.bunker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.bunker.databinding.FragmentStatsBinding

class StatsFragment : Fragment() {
    lateinit var binding: FragmentStatsBinding
    private val dataModel: DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStatsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backBtn.setOnClickListener {
            dataModel.message.value = MainMenuFragment()
        }

        val results = Game.getResults(view.context)
        binding.gamesCount.text = results.size.toString()
        if (results.size > 0) {
            binding.maxResult.text = results.max().toString()
            binding.avgResult.text = String.format("%.2f", results.average())
        } else {
            binding.maxResult.text = "0"
            binding.avgResult.text = "0"
        }
    }
}