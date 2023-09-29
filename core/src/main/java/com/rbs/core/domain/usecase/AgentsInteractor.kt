package com.rbs.core.domain.usecase

import com.rbs.core.data.Resource
import com.rbs.core.domain.model.Agents
import com.rbs.core.domain.repository.IAgentsRepository
import kotlinx.coroutines.flow.Flow

class AgentsInteractor(private val agentsRepository: IAgentsRepository): AgentsUseCase {

    override fun getAllAgents(): Flow<Resource<List<Agents>>> = agentsRepository.getAllAgents()

    override fun getFavoriteAgents(): Flow<List<Agents>> = agentsRepository.getFavoriteAgents()

    override fun setFavoriteAgents(agents: Agents, state: Boolean) = agentsRepository.setFavoriteAgents(agents, state)
}