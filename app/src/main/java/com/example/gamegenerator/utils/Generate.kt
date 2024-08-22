package com.example.gamegenerator.utils

import kotlin.random.Random

class Generate {
        fun generateNumber (): ArrayList<Int> {
                 val numbers = arrayListOf<Int>()

                while (numbers.size < 7){
                       var number =  Random.nextInt(0, 60)
                        if (!numbers.contains(number)) numbers.add(number)
                }
                numbers.sort()
                return  numbers
        }
}