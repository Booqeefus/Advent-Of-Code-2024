
import java.io.File

class Day6 {
    private val guardDirections = mapOf(
        Pair('v',Pair(1,0)),
        Pair('<',Pair(0,-1)),
        Pair('^',Pair(-1,0)),
        Pair('>',Pair(0,1))
    )

    private val guardTracing = mapOf(
        Pair('<','-'),
        Pair('>','-'),
        Pair('v','|'),
        Pair('^','|')
    )
    fun patrollingGuard() : Int{
        val map = ArrayList<ArrayList<Char>>()
        var guardX = -1
        var guardY = -1
        var guardDeltaX = 0
        var guardDeltaY = 0
        var guardVisual = '0'
        File("day6Input.txt").forEachLine{ //load map
            val chars = ArrayList(it.toCharArray().toList())
            map.add(chars)
        }

        for (y in map.indices){ // find where guard
            for (x in map[0].indices){
                if (map[y][x] in listOf('<','^','>','v')){
                    guardY = y
                    guardX = x
                    guardVisual = map[y][x]
                    guardDeltaX = guardDirections[guardVisual]!!.second
                    guardDeltaY = guardDirections[guardVisual]!!.first

                }
            }
        }

        val mapSize = map.size
        val resetGuardVis = guardVisual
        val resetGuardX = guardX
        val resetGuardY = guardY

        var possibleLoops = 0
        for (newObstY in map.indices){
            for (newObstX in map.indices){
                val mapCopy = mapDeepcopy(map)
                guardX = resetGuardX
                guardY = resetGuardY
                guardVisual = resetGuardVis
                guardDeltaX = guardDirections[guardVisual]!!.second
                guardDeltaY = guardDirections[guardVisual]!!.first

                mapCopy[newObstY][newObstX] = '#'


                while (true){
                    val nextGuardX = guardX + guardDeltaX
                    val nextGuardY = guardY + guardDeltaY
                    if(outOfBounds(nextGuardX,nextGuardY,mapSize)){ // out of bounds
                        break
                    }

                    val nextTile = mapCopy[nextGuardY][nextGuardX]

                    if (nextTile == '#'){ //wall

                    }

                    else if (nextTile == '.'){ // vacant

                    }

                    else{
                        if (nextTile == '+'){ // been here before
                            possibleLoops++
                            break
                        }
                        elseif

                    }


                }

            }
        }

        println("possible loops = $possibleLoops")
        return map.flatten().count{it == 'X'}
    }

    private fun outOfBounds(x : Int, y : Int, size : Int) : Boolean {
        return (x < 0 || x >= size || y < 0 || y >= size)
    }
    private fun mapDeepcopy( map : ArrayList<ArrayList<Char>>) :ArrayList<ArrayList<Char>>{
        val copy = ArrayList<ArrayList<Char>>()
        for (row in map){
            copy.add(ArrayList(row))
        }
        return copy
    }

    private fun changeDirection(currentDirection : Char) : Char{
        return when(currentDirection){
            'v' -> '<'
            '<' -> '^'
            '^' -> '>'
            else -> 'v'
        }
    }

}

fun main(){
    println(Day6().patrollingGuard())
}