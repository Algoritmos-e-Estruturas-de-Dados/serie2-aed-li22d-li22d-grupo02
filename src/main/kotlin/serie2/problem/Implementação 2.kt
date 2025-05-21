package serie2.problem

import serie2.part4.AEDHashMAP
import kotlin.time.Duration
import kotlin.time.measureTime


data class File(val file1: Boolean , var file2: Boolean)
fun main() {

    val elapsed: Duration = measureTime {
        ProcessPointsCollectionsI2.load("Files1/F1r.co", "Files2/F6r.co")
    }

    val elapsed1: Duration = measureTime {
        ProcessPointsCollectionsI2.union("outputU.co")
    }

    val elapsed2: Duration = measureTime {
        ProcessPointsCollectionsI2.intersection("outputI.co")
    }

    val elapsed3: Duration = measureTime {
        ProcessPointsCollectionsI2.difference(" outputD.co")
    }

    println ("load duration : $elapsed ")
    println ("union  duration : $elapsed1 ")
    println ("intersection  duration : $elapsed2 ")
    println ("difference duration : $elapsed3 ")
}

