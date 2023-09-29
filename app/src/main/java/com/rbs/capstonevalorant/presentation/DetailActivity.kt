package com.rbs.capstonevalorant.presentation

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.rbs.capstonevalorant.databinding.ActivityDetailBinding
import com.rbs.core.domain.model.Agents
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setData()
    }

    private fun setData() {
        val data = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(MainActivity.EXTRA_AGENTS, Agents::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(MainActivity.EXTRA_AGENTS)
        }

        if (data != null) {
            var favoriteStatus = data.favorite
            setLayout(data)
            setFavorite(favoriteStatus)

            binding.btnFavorite.setOnClickListener {
                favoriteStatus = !favoriteStatus
                detailViewModel.setFavoriteAgents(data, favoriteStatus)
                setFavorite(favoriteStatus)
            }
        }
    }

    private fun setLayout(data: Agents) {
        Glide.with(this)
            .load(data.image)
            .into(binding.ivDetailPhoto)

        binding.tvDetailName.text = data.name
        binding.tvDetailDescription.text = data.description
    }

    private fun setFavorite(status: Boolean) {
        if (status) {
            binding.btnFavorite.setBackgroundColor(com.google.android.material.R.attr.colorOnPrimary)
            binding.btnFavorite.setTextColor(Color.WHITE)
        } else {
            binding.btnFavorite.setBackgroundColor(com.google.android.material.R.attr.colorOnSurface)
            binding.btnFavorite.setTextColor(Color.BLACK)
        }
    }
}