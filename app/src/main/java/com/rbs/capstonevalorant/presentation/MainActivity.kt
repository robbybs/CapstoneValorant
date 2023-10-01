package com.rbs.capstonevalorant.presentation

import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.rbs.capstonevalorant.GlobalSingleton
import com.rbs.capstonevalorant.GlobalSingletonListener
import com.rbs.capstonevalorant.databinding.ActivityMainBinding
import com.rbs.core.domain.model.Agents
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel()
    private val listener = Listener()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setData()
    }

    override fun onStart() {
        super.onStart()
        GlobalSingleton.register(listener)
    }

    override fun onStop() {
        super.onStop()

        // we forget to unregister our listener so a reference
        // of the Singleton to the listener and to the Activity
        // still exists after the user navigates away fro that
        // activity

        GlobalSingleton.unregister(listener)
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

    // inner class has implicit reference to enclosing Activity
    private inner class Listener : GlobalSingletonListener {
        override fun onEvent() { }
    }
}