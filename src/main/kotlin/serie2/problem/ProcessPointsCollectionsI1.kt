package serie2.problem

import java.util.HashMap


object ProcessPointsCollectionsI1 {
    val map = HashMap<Point, List<String>>()


    // Recebe como parâmetro dois ficheiros de texto( file 1 e file 2 )
    fun load(file1: String, file2: String) {
        map.clear()
        // leitura do primeiro ficheiro
        val readerfile1 = createReader(file1)
        readerfile1.forEachLine { l ->
            val i = l.trim().split(" ")
            if ( "v" == i[0] && i.size >= 4) { // v - i[0] , id - i [1], x - i[2]  e y - i[3] i.size = 4
                val x = i[2].toFloat()
                val y = i[3].toFloat()
                val point = Point(x, y)
                val list = map[point]
                var listup: List<String>
                if (list == null) {
                    listup = listOf("file1")
                } else if ("file1" in list){
                    listup= list
                } else {
                    listup = list + "file1"
                }
                map[point] = listup
            }
        }
        readerfile1.close()

        // leitura do segundo ficheiro
        val readerfile2 = createReader(file2)
        readerfile2.forEachLine { l ->
            val i = l.trim().split(" ")
            if ("v" == i[0] && i.size >= 4) { // v - i[0] , id - i [1], x - i[2]  e y - i[3] i.size = 4
                val x = i[2].toFloat()
                val y = i[3].toFloat()
                val point = Point(x, y)
                val list = map[point]
                var listup: List<String>
                if (list == null) {
                    listup = listOf("file2")
                } else if ("file2" in list){
                        listup= list
                } else {
                        listup = list + "file2"
                }
                map[point] = listup

            }
        }
        readerfile2.close()
    }
    // permite produzir um novo ficheiro (com extensão .co) contendo os pontos, sem repetições,
    // que ocorram em pelo menos um dos ficheiros de entrada (operação union)
    fun union( output: String) {
        val writer = createWriter(output)
        for ((point, list) in map) {
            if ("file1" in list || "file2" in list) {
                writer.println("${point.x} ${point.y}")
            }
        }
        writer.close()
    }
    //permite produzir um novo ficheiro (outputI.co) contendo os pontos comuns entre
    //ambos os ficheiros de entrada (operação intersection).
    fun intersection(output: String) {
        val writer = createWriter(output)
        for ((point, list) in map) {
            if ("file1" in list && "file2" in list) {
                writer.println("${point.x} ${point.y}")
            }
        }
        writer.close()
    }
    // permite produzir um novo ficheiro (outputD.co) contendo os pontos únicos que estejam presentes
    // apenas em um dos ficheiros de input (operação difference)
    fun difference(output: String) {
        val writer = createWriter(output)
        for ((point, list) in map) {
            if ("file1" in list && "file2" !in list) {
                writer.println("${point.x} ${point.y}")
            }
        }
        writer.close()
    }

}