package serie2.problem
import kotlin.time.Duration
import kotlin.time.measureTime

data class Point (val x: Float, val y: Float)

fun main() {


    val elapsed: Duration = measureTime {
        ProcessPointsCollectionsI1.load("Files1/F1r.co", "Files2/F6r.co")
    }

    val elapsed1: Duration = measureTime {
        ProcessPointsCollectionsI1.union("outputU.co")
    }

    val elapsed2: Duration = measureTime {
        ProcessPointsCollectionsI1.intersection("outputI.co")
    }

    val elapsed3: Duration = measureTime {
        ProcessPointsCollectionsI1.difference(" outputD.co")
    }

    println ("load duration : $elapsed ")
    println ("union  duration : $elapsed1 ")
    println ("intersection  duration : $elapsed2 ")
    println ("difference duration : $elapsed3 ")
}
