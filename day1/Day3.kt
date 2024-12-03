import java.io.File

class Day3 {
    fun task1() : Int{
        var sum = 0
        val regexPattern = "mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\)".toRegex()
        val text = File("day3Input.txt").readText()
        var mulsEnabled = true
        for (match in regexPattern.findAll(text)){
            var matchString = match.value
            println(matchString)
            if (matchString == "do()"){
                mulsEnabled = true
            }
            else if (matchString == "don't()"){
                mulsEnabled = false
            }
            else if (mulsEnabled){
                matchString = matchString.substring(4,matchString.length - 1) //cut off front bit and last parenthesis
                val values = matchString.split(',')
                sum += values[0].toInt() * values[1].toInt()
            }


        }

        return sum
    }
}

fun main(){
    val d = Day3()
    println(d.task1())
}