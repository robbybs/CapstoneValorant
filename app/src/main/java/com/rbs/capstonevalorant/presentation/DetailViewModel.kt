package com.rbs.capstonevalorant.presentation

import androidx.lifecycle.ViewModel
import com.rbs.core.domain.model.Agents
import com.rbs.core.domain.usecase.AgentsUseCase

class DetailViewModel(private val agentsUseCase: AgentsUseCase) : ViewModel() {

    fun setFavoriteAgents(agents: Agents, favoriteStatus: Boolean) =
        agentsUseCase.setFavoriteAgents(agents, favoriteStatus)
}