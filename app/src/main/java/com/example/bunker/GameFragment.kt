package com.example.bunker

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.bunker.databinding.FragmentGameBinding


class GameFragment : Fragment() {
    lateinit var binding: FragmentGameBinding
    private val dataModel: DataModel by activityViewModels()
    lateinit var story: Story
    lateinit var result: Result

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.foodBar.progressDrawable.setColorFilter(Color.argb(1.0f, 0.0f, 1.0f, 0.0f), android.graphics.PorterDuff.Mode.SRC_IN)
        binding.healthBar.progressDrawable.setColorFilter(Color.argb(1.0f, 1.0f, 0.0f, 0.0f), android.graphics.PorterDuff.Mode.SRC_IN)
        binding.menuBtn.setOnClickListener {
            dataModel.message.value = MainMenuFragment()
        }

        day()

        binding.noBtn.setOnClickListener {
            result = Stories.noResult
            night(view)
        }

        binding.yesBtn.setOnClickListener {
            result = story.yesResult
            night(view)
        }

        binding.nextBtn.setOnClickListener {
            day()
        }
    }

    fun day() {
        story = Stories.getStory()
        binding.dayView.text = "День ${Game.day}"
        binding.resultText.visibility = View.GONE
        binding.nextBtn.visibility = View.GONE
        binding.startText.visibility = View.VISIBLE
        binding.linearLayout3.visibility = View.VISIBLE
        binding.startText.text = story.startText
        binding.healthBar.progress = Game.health
        binding.foodBar.progress = Game.food
    }

    fun night(view: View) {
        if (result.resType == 0 && result.count > 0) {
            binding.resultText.text = "+${result.count} еды"
            binding.resultText.setTextColor(Color.argb(1.0f, 0.0f, 1.0f, 0.0f))
            Game.food += result.count
        } else if (result.resType == 0 && result.count < 0) {
            binding.resultText.text = "${result.count} еды"
            binding.resultText.setTextColor(Color.argb(1.0f, 1.0f, 0.0f, 0.0f))
            Game.food += result.count
        } else if (result.resType == 1 && result.count > 0) {
            binding.resultText.text = "+${result.count} здоровья"
            binding.resultText.setTextColor(Color.argb(1.0f, 0.0f, 1.0f, 0.0f))
            Game.health += result.count
        } else if (result.resType == 1 && result.count < 0) {
            binding.resultText.text = "${result.count} здоровья"
            binding.resultText.setTextColor(Color.argb(1.0f, 1.0f, 0.0f, 0.0f))
            Game.health += result.count
        }

        binding.resultText.visibility = View.VISIBLE
        binding.nextBtn.visibility = View.VISIBLE
        binding.startText.visibility = View.VISIBLE
        binding.linearLayout3.visibility = View.GONE
        binding.startText.text = result.text
        binding.healthBar.progress = Game.health
        binding.foodBar.progress = Game.food

        Game.day += 1
        Game.save(view.context.getSharedPreferences("game", Context.MODE_PRIVATE))
    }
}