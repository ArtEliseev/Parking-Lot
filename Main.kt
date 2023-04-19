package parking

import kotlin.system.exitProcess

data class Spot(var availability: Boolean, var car: Car? = null)
class Car(val number: String, val color: String)

fun main() {
    var spots = createSpots(readln())
    while (true) {
        val input = readln()
        when (input.split(" ").first()) {
            "park" -> spots = park(input, spots)
            "leave" -> spots = leave(input, spots)
            "create" -> spots = createSpots(input)
            "status" -> status(spots)
            "reg_by_color", "spot_by_color" -> regOrSpotByColor(input, spots)
            "spot_by_reg" -> spotByReg(input, spots)
            "exit" -> exitProcess(0)
        }
    }
}

fun createSpots(input: String): MutableList<Spot> {
    if (input == "exit") {
        exitProcess(0)
    } else if (!input.matches("create \\d+".toRegex())) {
        println("Sorry, a parking lot has not been created.")
        main()
    }
    val number = input.split(" ").last().toInt()
    println("Created a parking lot with $number spots.")
    return MutableList(number) { Spot(true) }
}

fun park(input: String, spots: MutableList<Spot>): MutableList<Spot> {
    val (park, number, color) = input.split(" ")
    val activeSpot: Spot
    var counter = 0
    for (spot in spots) {
        if (spot.availability) {
            activeSpot = spot
            val car = Car(number.uppercase(), color.uppercase())
            activeSpot.availability = false
            activeSpot.car = car
            println("$color car parked in spot ${spots.indexOf(activeSpot) + 1}.")
            break
        } else {
            counter++
            if (counter == spots.size) println("Sorry, the parking lot is full.")
        }
    }
    return spots
}

fun leave(input: String, spots: MutableList<Spot>): MutableList<Spot> {
    val number = input.split(" ").last().toInt()
    if (!spots[number - 1].availability) {
        println("Spot $number is free.")
        spots[number - 1].availability = true
        spots[number - 1].car = null
    }
    else {
        println("There is no car in spot $number.")
    }
    return spots
}

fun status(spots: MutableList<Spot>) {
    var counter = 0
    for (spot in spots) {
        if (!spot.availability) println("${spots.indexOf(spot) + 1} ${spot.car?.number} ${spot.car?.color}")
        else counter++
    }
    if (counter == spots.size) println("Parking lot is empty.")
}

fun regOrSpotByColor(input: String, spots: MutableList<Spot>) {
    val (method, color) = input.uppercase().split(" ")
    when (method) {
        "REG_BY_COLOR" -> {
            var list = mutableListOf<String>()
            for (spot in spots) if (spot.car?.color == color) list.add(spot.car!!.number)
            if (list.isEmpty()) println("No cars with color $color were found.")
            else println(list.joinToString(", "))
        }
        "SPOT_BY_COLOR" -> {
            var list = mutableListOf<String>()
            for (spot in spots) if (spot.car?.color == color) list.add("${spots.indexOf(spot) + 1}")
            if (list.isEmpty()) println("No cars with color $color were found.")
            else println(list.joinToString(", "))
        }
    }
}

fun spotByReg(input: String, spots: MutableList<Spot>) {
    val reg = input.uppercase().split(" ").last()
    var counter = 0
    for (spot in spots) if (spot.car?.number.equals(reg)) println("${spots.indexOf(spot) + 1}")
    else counter++
    if (counter == spots.size) println("No cars with registration number $reg were found.")
}