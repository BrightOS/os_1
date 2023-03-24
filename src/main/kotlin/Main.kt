import java.io.File

fun parseInputFile(file: File) =
    arrayListOf<Process>().apply {
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
            "${process.name} ${process.numberOfTicks} ${process.priority} ${process.afterWhatTick} "
        )
        ticks.subList(0, ticks.indexOfLast { it.contains(process.name) } + 1).forEach {
            print(if (it.contains(process.name)) "И" else "Г")
        }
        println()
    }

    println(ticks)
}

data class Process(
    val name: String,
    val numberOfTicks: Int,
    val priority: Int,
    val afterWhatTick: Int
)