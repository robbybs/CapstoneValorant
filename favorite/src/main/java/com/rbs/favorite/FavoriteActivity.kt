package com.rbs.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

import com.rbs.capstonevalorant.presentation.DetailActivity
import com.rbs.capstonevalorant.presentation.MainActivity
import com.rbs.capstonevalorant.presentation.MainAdapter
import com.rbs.core.domain.model.Agents
import com.rbs.favorite.databinding.ActivityFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadKoinModules(favoriteModule)
        setData()
    }

    private fun setData() {
        val mainAdapter = MainAdapter()

        favoriteViewModel.getFavoriteAgents.observe(this) {
            mainAdapter.setData(it)
            binding.emptyList.root.visibility = if (it.isNotEmpty()) View.GONE else View.VISIBLE
        }

        mainAdapter.onItemClick = {
            val agents = Agents(
                it.id,
                it.name,
                it.description,
                it.image,
                it.favorite
            )
            startActivity(
                Intent(this@FavoriteActivity, DetailActivity::class.java).putExtra(
                    MainActivity.EXTRA_AGENTS,
                    agents
                )
            )
        }

        with(binding.rvAgent) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mainAdapter
        }
    }
}