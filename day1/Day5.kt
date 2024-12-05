import java.io.File

class Day5 {
    fun task1() : Int{
        val prerequisitePages = HashMap<Int,HashSet<Int>>() // entry x :set(a,b) means that in order for x to be ok a,b need to already be in place if present
        var middleNumbersSum = 0
        var fixedUpdatesSum = 0
        val text = File("day5Input.txt").readLines()

        val rules = text.subList(0,text.indexOf(""))
        val updates = text.subList(text.indexOf("") + 1, text.size)

        for (rule in rules){ // get all the rules sorted and slapped into the map
            val items = rule.split('|')
            val key = items[1].toInt()
            val valueToAdd = items[0].toInt()
            if (key !in prerequisitePages.keys){
                prerequisitePages[key] = HashSet()
            }
            prerequisitePages[key]!!.add(valueToAdd)
        }

        for (update in updates){
            var updateIsOK = true
            val updatedPages = update.split(',').map { it.toInt() }.toList()
            for (i in 0 until updatedPages.size - 1){
                var shouldContinue = true
                for (j in i + 1 until updatedPages.size){
                    val left = updatedPages[i]
                    var right = updatedPages[j]
                    if (right in prerequisitePages[left]!!){
                        updateIsOK = false
                        shouldContinue = false
                        break
                    }
                }
                if (!shouldContinue){
                    break
                }
            }
            if (updateIsOK){
                middleNumbersSum += updatedPages[updatedPages.size / 2]
            }
            else{
                fixedUpdatesSum += fixBadUpdate(ArrayList(updatedPages),prerequisitePages)
            }

        }
        println("bad $fixedUpdatesSum")
        return middleNumbersSum
    }
    fun fixBadUpdate(pages : ArrayList<Int>,rules : HashMap<Int,HashSet<Int>>): Int{ // i am so deeply sorry for this but hey it works
        var updateIsOK = false
        while (!updateIsOK) {
            updateIsOK = true
            var leftSwap = -1
            var rightSwap = -1
            for (i in 0 until pages.size - 1) {
                var shouldContinue = true
                for (j in i + 1 until pages.size) {
                    val left = pages[i]
                    var right = pages[j]
                    if (right in rules[left]!!) {
                        updateIsOK = false
                        leftSwap = i
                        rightSwap = j
                        shouldContinue = false
                        break
                    }
                }
                if (!shouldContinue) {
                    break
                }
            }
            if (leftSwap != -1) {
                val tmp = pages[leftSwap]
                pages[leftSwap] = pages[rightSwap]
                pages[rightSwap] = tmp
            }
        }
        return pages[pages.size / 2]

    }
}



fun main(){
    print(Day5().task1())

}