package com.example.gamegenerator.entities

import java.io.IOException
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*

data class Game (
    val hash: String,
    val name: String,
    var select: Boolean,
    var max: Int,
    var maxNumber: Int,
)

class GameList {
    private val games = arrayListOf<Game>()

    constructor(){}

    fun generate(){
        games.add(Game(md5(Date().time.toString()),"Mega-Sena",false,  6,60))
        games.add(Game(md5(Date().time.toString()),"Lotofácil",false,15,25))
        games.add(Game(md5(Date().time.toString()),"Quina",false,5, 80))
        games.add(Game(md5(Date().time.toString()),"Lotomania",false,20,50))
        games.add(Game(md5(Date().time.toString()),"Dupla Sena",false,12,50))
        games.add(Game(md5(Date().time.toString()),"Timemania",false,7, 80))
        games.add(Game(md5(Date().time.toString()),"Dia de Sorte",false,7, 60))
        games.add(Game(md5(Date().time.toString()),"Super Sete",false,7,70))
        games.add(Game(md5(Date().time.toString()),"+Milionária",false,6,50))
        games.add(Game(md5(Date().time.toString()),"Loteca",false,1,10))
        games.add(Game(md5(Date().time.toString()),"Federal",false,1,10))
    }

    private fun md5(input:String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }

    fun getList():List<Game> {
        return games
    }

    fun getSelect(): Game  {
        var select = games.filter { it.select }
        if (select.isNotEmpty()) return select[0]
        return throw  Exception();
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




