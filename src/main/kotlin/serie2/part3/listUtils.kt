package serie2.part3
//tipo Node<T> tem 3 campos: um value do tipo T e duas referências,
// previous e next, para o elemento anterior e seguinte.
class Node<T> (
    var value: T = Any() as T,
    var next: Node<T>? = null,
    var previous: Node<T>? = null) {
}
// reorganiza a lista de modo que todos os números pares fiquem consecutivos no início da lista
fun splitEvensAndOdds(list:Node<Int>){
        var x = list.next
        while (x != list) {
            val next = x!!.next
            if(x.value % 2 ==0){ // se o x é um número par colocar no  início da lista
                x.previous!!.next = x.next
                x.next!!.previous = x.previous
                  x.next= list.next
                  x.previous= list
                  list!!.next!!.previous = x
                  list.next=x
            }
            x = next
        }
}
// fiz esta função para manter a função intersection mais limpa
// A função remove um nó x  de uma lista duplamente ligada circular com sentinela.
fun <T> remove(x: Node<T>) {
    x.previous!!.next = x.next
    x.next!!.previous = x.previous
    x.previous = null
    x.next = null
}

fun <T> intersection(list1: Node<T>, list2: Node<T>, cmp: Comparator<T>): Node<T>? {
    var lastValue: T? = null // ultimo valor da lista adicionado
    var head: Node<T>? = null //  primeiro elemento da lista
    var tail: Node<T>? = null //  último elemento da lista
    //duas listas duplamente ligadas, circulares e com sentinela, referenciadas por list1 e list2
    var el1L1 = list1.next
    var el1L2 = list2.next
    while (el1L1 !== list1 && el1L2 !== list2) {
        val s = cmp.compare(el1L1!!.value, el1L2!!.value) //ordenadas de modo crescente segundo o comparador cmp
          if (s < 0) {
            el1L1 = el1L1.next
          } else if (s > 0) {
            el1L2 = el1L2.next
          } else {
            val value = el1L1.value

            var i = el1L1
              //remove todos os nós com valor value na lista 1
                while (i !== list1 && cmp.compare(i!!.value, value) == 0){
                            val next =i!!.next
                             remove(i)
                    if (cmp.compare(value, lastValue) != 0 || null == head ) { // adiciona se ainda não existe no resultado
                        if (null == head) {
                                  head = i
                        } else {
                                  tail!!.next = i
                                       i.previous = tail
                        }
                        lastValue =value
                        tail = i
                    }
                    i = next
                }
                el1L1 = i
              // aqui é o mesmo processo: remove todos os nós com valor value na lista 2 invés da lista 1
                while (  cmp.compare(el1L2!!.value, value) == 0 && el1L2 !== list2) {
                    val next = el1L2.next
                    remove(el1L2)
                    el1L2 = next
                }
            }
    }
    return head // retorna uma nova lista com os elementos que estejam simultaneamente presentes em ambas as listas, removendo-os de list1 e list2.
}
