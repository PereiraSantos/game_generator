package com.example.gamegenerator.utils

import kotlin.random.Random

class Generate {
        fun generateNumber (max: Int, maxNumber: Int): ArrayList<Int> {
                 val numbers = arrayListOf<Int>()

                while (numbers.size <= max){
                       var number =  Random.nextInt(0, maxNumber)
                        if (!numbers.contains(number)) numbers.add(number)
                }
                numbers.sort()
                return  numbers
        }
}