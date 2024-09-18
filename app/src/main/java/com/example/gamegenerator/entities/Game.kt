package com.example.gamegenerator.entities

import java.math.BigInteger
import java.security.MessageDigest
import java.util.*

data class Game (
    val hash: String,
    val name: String,
    var select: Boolean,
)

class GameList {
    private val games = arrayListOf<Game>()

    constructor(){}

    fun generate(){
        games.add(Game(md5(Date().time.toString()),"Mega-Sena",false))
        games.add(Game(md5(Date().time.toString()),"Lotofácil",false))
        games.add(Game(md5(Date().time.toString()),"Quina",false))
        games.add(Game(md5(Date().time.toString()),"Lotomania",false))
        games.add(Game(md5(Date().time.toString()),"Dupla Sena",false))
        games.add(Game(md5(Date().time.toString()),"Timemania",false))
        games.add(Game(md5(Date().time.toString()),"Dia de Sorte",false))
        games.add(Game(md5(Date().time.toString()),"Super Sete",false))
        games.add(Game(md5(Date().time.toString()),"+Milionária",false))
        games.add(Game(md5(Date().time.toString()),"Loteca",false))
        games.add(Game(md5(Date().time.toString()),"Federal",false))
    }

    private fun md5(input:String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }

    fun getList():List<Game> {
        return games
    }

    fun getSelect(): String {
        var select = games.filter { it.select }
        return select[0].name
    }

    fun updateList(game: Game){
        for (index in games.indices ){
            if (games[index].hash == game.hash) {
                games[index].select = !games[index].select
            } else {
                games[index].select = false
            }
        }
    }
}




