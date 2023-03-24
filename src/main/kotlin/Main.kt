import java.io.File

fun parseInputFile(file: File) = arrayListOf<Process>().apply {
    file.forEachLine {
        it.split(" ").let {
            add(
                Process(
                    name = it[0],
                    numberOfTicks = it[1].toInt(),
                    priority = it[2].toInt(),
                    afterWhatTick = it[3].toInt()
                )
            )
        }
    }
}

fun main() {
    var numberOfWaitTicks = 0
    var numberOfAbstractTicks = 0
    val processList = parseInputFile(
//        File("test1")
        File("test2")
    )

    val ticks = arrayListOf<String>()
    processList.forEach { process ->
        if (process.priority > 0)
            repeat(process.numberOfTicks) {
                ticks.add(process.afterWhatTick, process.name)
            }
        else
            repeat(process.numberOfTicks) {
                ticks.add(process.name)
            }
    }

    processList.forEach { process ->
        print(
            "${process.name} ${process.numberOfTicks} ${process.afterWhatTick} "
        )
        ticks.subList(0, ticks.indexOfLast { it.contains(process.name) } + 1).forEach {
            print(it.contains(process.name).let {
                if (!it) numberOfWaitTicks++
                numberOfAbstractTicks++
                if (it) "И" else "Г"
            })
        }
        println()
    }

//    println(ticks)
    println("Efficiency: ${"%.${2}f".format((numberOfWaitTicks.toFloat() / numberOfAbstractTicks * 100))}%")
}

data class Process(
    val name: String,
    val numberOfTicks: Int,
    val priority: Int,
    val afterWhatTick: Int
)