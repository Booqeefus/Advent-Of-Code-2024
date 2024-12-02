import java.io.File
import kotlin.math.abs

class Day2 {
    fun task1(path : String) : Int{
        var safeReports = 0
        File(path).forEachLine {
            if(isReportSafe(it.split(' ').map{x -> x.toInt()}.toList())){
                safeReports++
            }
        }
        return safeReports
    }
    private fun isReportSafe(levels : List<Int> ) : Boolean {
        println(levels)
        var isDescending : Boolean? = null
        for (i  in 0 until levels.size - 1){
            val delta = levels[i + 1] - levels[i]

            if (isDescending == null){
                isDescending = delta < 0
            }
            val isDescendingLocally = delta < 0
            if (isDescendingLocally != isDescending || abs(delta) !in 1 .. 3){

                return false
            }
        }
        return true
    }

    fun task2(path : String): Int{
        var safeReports = 0
        File(path).forEachLine {
            var canPass = false
            val levels = it.split(' ').map{x -> x.toInt()}.toList()
            for (i in levels.indices){
                val cpy = levels.filterIndexed { index, _ -> index != i  }.toList()
                canPass = isReportSafe(cpy) || canPass
                if (canPass){
                    safeReports++
                    break
                }

            }
        }
        return safeReports
    }

}

fun main(){
    val d = Day2()
    println(d.task1("day2input.txt"))
    println(d.task2("day2input.txt"))
}