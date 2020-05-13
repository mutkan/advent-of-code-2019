import com.aoc.intcode.tractorbeam.DroneSystem
import input.Day
import input.InputReader

fun main() {
    val input = InputReader().readInputAsSingleString(Day.from(19))
    val system = DroneSystem(input)
    val scan = system.scanAreaSurroundingEmitter(50)
    println("Solution Part 1: ${scan.getPointsAffectedByBeam()}")
    println("Solution Part 2: ${system.scanAreaForSantasShip()}")

    //4441000 is too high!
}