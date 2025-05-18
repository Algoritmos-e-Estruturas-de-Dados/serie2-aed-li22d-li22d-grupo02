package serie2.problem

import serie2.part4.AEDHashMAP


object ProcessPointsCollectionsI2 {
    val map = AEDHashMAP<Point, File>()

    // Recebe como parâmetro dois ficheiros de texto( file 1 e file 2 )
    fun load(file1: String, file2: String) {
        // ler o primeiro ficheiro
        val readerfile1 = createReader(file1)
        readerfile1.forEachLine { l ->
            val i = l.trim().split(" ")
            if ( "v" == i[0] && i.size >= 4) { // v - i[0] , id - i [1], x - i[2]  e y - i[3] i.size = 4
                val x = i[2].toInt()
                val y = i[3].toInt()
                val point = Point(x, y)
                val list = map.get(point)
                if (list == null) {
                    map.put(point, File(file1 = true, file2 = false))
                } else {
                    map.put(point, list.copy(file1 = true))
                }
            }
        }
        readerfile1.close()

        val readerfile2 = createReader(file2)
        readerfile2.forEachLine { l ->
            val i = l.trim().split(" ")
            if ( "v" == i[0] && i.size >= 4) { // v - i[0] , id - i [1], x - i[2]  e y - i[3] i.size = 4
                val x = i[2].toInt()
                val y = i[3].toInt()
                val point = Point(x, y)
                val list = map.get(point)
                if (list == null) {
                    map.put(point, File(file1 = false, file2 = true))
                } else {
                    map.put(point, list.copy(file2 = true))
                }
            }
        }
        readerfile2.close()
    }
    // permite produzir um novo ficheiro (com extensão .co) contendo os pontos, sem repetições,
    // que ocorram em pelo menos um dos ficheiros de entrada (operação union)
    fun union(output: String) {
        val writer = createWriter(output)
        for (item in map) {
            val point = item.key
            val file = item.value
            if (file.file1 || file.file2) {
                writer.println("${point.x} ${point.y}")
            }
        }
        writer.close()
    }
    //permite produzir um novo ficheiro (outputI.co) contendo os pontos comuns entre
    //ambos os ficheiros de entrada (operação intersection).
    fun intersection(output: String) {
        val writer = createWriter(output)
        for (item in map) {
            val point = item.key //
            val file = item.value
            if (file.file1 && file.file2) {
                writer.println("${point.x} ${point.y}")
            }
        }
        writer.close()
    }
    // permite produzir um novo ficheiro (outputD.co) contendo os pontos únicos que estejam presentes
    // apenas em um dos ficheiros de input (operação difference)
    fun difference(output: String) {
        val writer = createWriter(output)
        for (item in map) {
            val point = item.key
            val file = item.value
            if (file.file1 && !file.file2) {
                writer.println("${point.x} ${point.y}")
            }
        }
        writer.close()
    }
}

