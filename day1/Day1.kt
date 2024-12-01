import java.io.File
import kotlin.math.abs

class Day1 {
    fun task1(path : String): Int{
        val leftList = ArrayList<Int>()
        val rightList = ArrayList<Int>()
        File(path).forEachLine {

            val lineContent = it.split(' ')

            leftList.add(lineContent[0].toInt())
            rightList.add(lineContent[3].toInt())
        }


        val sortedLeft = leftList.sorted()
        val sortedRight = rightList.sorted()
        var totalDifference = 0
        for (i in sortedLeft.indices){
            val leftItem = sortedLeft[i]
            val rightItem = sortedRight[i]
            totalDifference += abs(leftItem - rightItem)
        }
        return totalDifference
    }


    fun task2(path : String): Int{
        val leftList = ArrayList<Int>()
        val rightList = ArrayList<Int>()
        File(path).forEachLine {

            val lineContent = it.split(' ')

            leftList.add(lineContent[0].toInt())
            rightList.add(lineContent[3].toInt())
        }
        var totalSimilarity = 0
        for (i in leftList){
            val occurrences = rightList.stream().filter{it == i}.count()
            totalSimilarity += (i * occurrences).toInt()
        }
        return totalSimilarity
    }

}
fun main(){
    val d = Day1()
    println(d.task1("task1Input.txt"))
    println(d.task2("task1Input.txt"))
}

