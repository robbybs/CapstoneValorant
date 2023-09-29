package com.rbs.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rbs.core.data.local.entity.AgentsEntity

@Database(entities = [AgentsEntity::class], version = 1, exportSchema = false)
abstract class AgentsDatabase : RoomDatabase() {

    abstract fun agentsDao(): AgentsDao
}