package com.rbs.capstonevalorant.presentation

import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.rbs.capstonevalorant.databinding.ActivityMainBinding
import com.rbs.core.domain.model.Agents
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setData()
    }

    private fun setData() {
        val mainAdapter = MainAdapter()

        mainViewModel.getAgents.observe(this) {
            it.data?.let { agentList -> mainAdapter.setData(agentList) }
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
                Intent(this@MainActivity, DetailActivity::class.java).putExtra(
                    EXTRA_AGENTS,
                    agents
                )
            )
        }

        with(binding.rvAgent) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mainAdapter
        }

        binding.fabFavorite.setOnClickListener {
            startActivity(Intent(this, Class.forName("com.rbs.favorite.FavoriteActivity")))
        }
    }

    companion object {
        const val EXTRA_AGENTS = "extras"
    }
}