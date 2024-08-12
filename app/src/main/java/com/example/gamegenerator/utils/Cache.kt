package com.example.gamegenerator.utils

import com.example.gamegenerator.entities.GameList

class Cache private constructor() {
    companion object {

        @Volatile
        private var instance: Cache? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: Cache().also { instance = it }
            }
    }

    private val gameList = GameList()

    fun getGameInstance(): GameList {
        return gameList
    }
}