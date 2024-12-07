import java.io.File
class Day7 {
    fun doStuff(): Long{
        var sumOfPossibleEquations = 0L
        File("day7Input.txt").forEachLine {
            val equationTarget = it.substring(0,it.indexOf(':')).toLong()
            val numbers = it.substring(it.indexOf(':') + 2).split(' ').map { c -> c.toLong() }.toList()
            if (checkIfEquationIsPossible(equationTarget,numbers)){
                sumOfPossibleEquations += equationTarget
            }
        }



        return sumOfPossibleEquations
    }

    private fun checkIfEquationIsPossible(target : Long, numbers : List<Long>) : Boolean{
        return equationBuildingRecursion(target,numbers[0],1,numbers)
    }

    private fun equationBuildingRecursion(target: Long, subTotal : Long, indexOfNumberToProcess : Int, numbers: List<Long>) : Boolean{
        val nextNumber = numbers[indexOfNumberToProcess]

        if (indexOfNumberToProcess + 1 == numbers.size){ // base case
            return (subTotal + nextNumber == target
                    ||
                    subTotal * nextNumber == target
                    ||
                    concatenate(subTotal,nextNumber) == target)
        }
        else{
            return (
                    equationBuildingRecursion(target, subTotal + nextNumber, indexOfNumberToProcess + 1, numbers)
                            ||
                    equationBuildingRecursion(target,subTotal * nextNumber, indexOfNumberToProcess + 1, numbers)
                            ||
                    equationBuildingRecursion(target,concatenate(subTotal,nextNumber),indexOfNumberToProcess + 1,numbers)
                    )
        }

    }

    private fun concatenate(n1 : Long, n2 : Long) : Long = (n1.toString() + n2.toString()).toLong()

}



fun main(){
    println(Day7().doStuff())
}