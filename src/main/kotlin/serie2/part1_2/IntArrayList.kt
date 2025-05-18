package serie2.part1_2

class IntArrayList(dim: Int) {
    val arr = IntArray(dim)
    var head = 0
    var tail = 0
    var size = 0
    var offset=0
    //Adiciona o inteiro x ao final da lista.
    // Retorna true se a operação for bem-sucedida e false caso a lista esteja cheia
    fun append (x: Int): Boolean { // complexidade:O(1)
        if (size == arr.size) return false
        arr[tail] = x - offset //
        tail = (tail + 1) % arr.size
        size= size + 1
        return true
    }

    //Retorna o enésimo elemento da lista ou null caso o índice seja inválido
    fun get(n: Int): Int? { // complexidade : O(1)
        return if (n in 0 .. arr.size -1 ) arr[(head + n) % arr.size] + offset else null
    }
    //Adiciona x a todos os inteiros presentes nesta lista.
    fun addToAll(x: Int)   { //complexidade : O(1)
        offset = offset + x
    }

    fun remove(): Boolean { //complexidade : O(1)
        if (size == 0) return false
        head=(head+1)% arr.size
        size=size-1
        return true
    }

}