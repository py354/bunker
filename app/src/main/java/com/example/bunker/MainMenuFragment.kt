package com.example.bunker

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import com.example.bunker.databinding.FragmentMainMenuBinding

// TODO: Rename parameter arg
class MainMenuFragment : Fragment() {
    private val dataModel: DataModel by activityViewModels()
    lateinit var binding: FragmentMainMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainMenuBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.settings).setOnClickListener{
            dataModel.message.value = SettingsFragment()
        }

        view.findViewById<Button>(R.id.new_game).setOnClickListener{
            Game.newGame()
            dataModel.message.value = GameFragment()
        }

        view.findViewById<Button>(R.id.continue_game).setOnClickListener{
            Game.complete(view.context.getSharedPreferences("game", Context.MODE_PRIVATE))
            dataModel.message.value = GameFragment()
        }

        view.findViewById<Button>(R.id.stats).setOnClickListener{
            dataModel.message.value = StatsFragment()
        }
    }
}