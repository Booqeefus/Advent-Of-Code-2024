import java.io.File

class Day4 {
    fun doStuff(): Int{
        var occurences = 0
        val wallOfText = ArrayList<ArrayList<Char>>()
        File("day4Input.txt").forEachLine {
            wallOfText.add(
                ArrayList(it.toCharArray().toList())
            )
        }

        //Rows
        for (line in wallOfText){
            for (i in 0 until  line.size - 3){
                val sample = line.subList(i,i + 4)
                val sampleString = sample.joinToString("")
                val reverse = sampleString.reversed()
                if (sampleString == "XMAS" || reverse == "XMAS"){
                    occurences++
                }
            }
        }

        //Cols (This relies heavily on all rows being the same length)
        for (row in 0 until wallOfText.size - 3){
            for(col in 0 until wallOfText.size){
                val sample = listOf(
                    wallOfText[row][col],
                    wallOfText[row + 1][col],
                    wallOfText[row + 2][col],
                    wallOfText[row + 3][col]
                )
                val sampleString = sample.joinToString("")
                val reverse = sampleString.reversed()
                if (sampleString == "XMAS" || reverse == "XMAS"){
                    occurences++
                }

            }
        }

        //Top left Bottom right Diag
        for (i in 0 until wallOfText.size - 3){
            for (j in 0 until wallOfText.size - 3){
                val sample = listOf(
                    wallOfText[i][j],
                    wallOfText[i + 1][j + 1],
                    wallOfText[i + 2][j + 2],
                    wallOfText[i + 3][j + 3]
                )
                val sampleString = sample.joinToString("")
                val reverse = sampleString.reversed()
                if (sampleString == "XMAS" || reverse == "XMAS"){
                    occurences++
                }
            }
        }

        //Bottom Left to top right
        for (i in 0 until wallOfText.size - 3){
            for (j in 3 until  wallOfText.size){
                val sample = listOf(
                    wallOfText[i][j],
                    wallOfText[i + 1][j - 1],
                    wallOfText[i + 2][j - 2],
                    wallOfText[i + 3][j - 3]
                )
                val sampleString = sample.joinToString("")
                val reverse = sampleString.reversed()
                if (sampleString == "XMAS" || reverse == "XMAS"){
                    occurences++
                }
            }
        }



        return occurences

    }

    fun doOtherStuff() : Int{
        var occurences = 0
        val wallOfText = ArrayList<ArrayList<Char>>()
        File("day4Input.txt").forEachLine {
            wallOfText.add(
                ArrayList(it.toCharArray().toList())
            )
        }
        for (i in 0 until wallOfText.size - 2){
            for (j in 0 until wallOfText.size - 2){
                val sample1 = listOf(
                    wallOfText[i][j],
                    wallOfText[i + 1][j + 1],
                    wallOfText[i + 2][j + 2]
                )
                val sample2 = listOf(
                    wallOfText[i + 2][j],
                    wallOfText[i + 1][j + 1],
                    wallOfText[i][j + 2]
                )
                if (isValid(sample1) && isValid(sample2)){
                    occurences++
                }
            }
        }

        return occurences
    }

    fun isValid(list : List<Char>) : Boolean{
        val string = list.joinToString("")
        return (string == "MAS" || string.reversed() == "MAS")
    }
}

fun main(){
    println(Day4().doStuff())
    println(Day4().doOtherStuff())

}