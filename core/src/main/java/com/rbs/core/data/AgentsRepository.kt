package com.rbs.core.data

import com.rbs.core.data.local.LocalDataSource
import com.rbs.core.data.remote.RemoteDataSource
import com.rbs.core.data.remote.network.ApiResponse
import com.rbs.core.data.remote.response.AgentsResponse
import com.rbs.core.domain.model.Agents
import com.rbs.core.domain.repository.IAgentsRepository
import com.rbs.core.utils.AppExecutors
import com.rbs.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AgentsRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IAgentsRepository {

    override fun getAllAgents(): Flow<Resource<List<Agents>>> =
        object : NetworkBoundResource<List<Agents>, List<AgentsResponse>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Agents>> =
                localDataSource.getAllAgents().map { DataMapper.mapEntitiesToDomain(it) }

            override suspend fun createCall(): Flow<ApiResponse<List<AgentsResponse>>> =
                remoteDataSource.getAgentsList()

            override suspend fun saveCallResult(data: List<AgentsResponse>) {
                val agentsList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertAgents(agentsList)
            }

            override fun shouldFetch(data: List<Agents>?): Boolean = true

        }.asFlow()

    override fun getFavoriteAgents(): Flow<List<Agents>> =
        localDataSource.getFavoriteAgents().map { DataMapper.mapEntitiesToDomain(it) }

    override fun setFavoriteAgents(agents: Agents, state: Boolean) {
        val agentsEntity = DataMapper.mapDomainToEntity(agents)
        appExecutors.diskIO().execute { localDataSource.setFavoriteAgents(agentsEntity, state) }
    }
}