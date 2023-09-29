package com.rbs.core.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rbs.core.data.local.entity.AgentsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AgentsDao {
    @Query("SELECT * FROM agents")
    fun getAgentsList(): Flow<List<AgentsEntity>>

    @Query("SELECT * FROM agents where favorite = 1")
    fun getFavoriteAgents(): Flow<List<AgentsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAgents(agents: List<AgentsEntity>)

    @Update
    fun updateFavoriteAgents(agents: AgentsEntity)
}