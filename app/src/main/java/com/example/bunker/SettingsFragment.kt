package com.example.bunker

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.activityViewModels
import com.example.bunker.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private val dataModel: DataModel by activityViewModels()
    private val musicDataModel: MusicDataModel by activityViewModels()
    lateinit var binding: FragmentSettingsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = view.context.getSharedPreferences("settings", Context.MODE_PRIVATE)

        binding.musicBtn.isChecked = sharedPref.getBoolean("music", true)
        binding.musicBtn.setOnCheckedChangeListener { _, b ->
            with(sharedPref.edit()) {
                putBoolean("music", b)
                apply()
            }
            musicDataModel.message.value = null
        }

        binding.seekBar.progress = sharedPref.getInt("volume", 10)
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                with(sharedPref.edit()) {
                    putInt("volume", progress)
                    apply()
                }
                musicDataModel.message.value = null
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        binding.back.setOnClickListener{
            dataModel.message.value = MainMenuFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater)
        return binding.root
    }
}