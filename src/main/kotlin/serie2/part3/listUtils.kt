package serie2.part3

class Node<T> (
    var value: T = Any() as T,
    var next: Node<T>? = null,
    var previous: Node<T>? = null) {
}

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

fun <T> remove(x: Node<T>) {
    x.previous!!.next = x.next
    x.next!!.previous = x.previous
    x.previous = null
    x.next = null
}

fun <T> intersection(list1: Node<T>, list2: Node<T>, cmp: Comparator<T>): Node<T>? {



    var head: Node<T>? = null
    var tail: Node<T>? = null
    var lastValue: T? = null
    //duas listas duplamente ligadas, circulares e com sentinela, referenciadas por list1 e list2
    var el1L1 = list1.next
    var el1L2 = list2.next


    while (el1L1 !== list1 && el1L2 !== list2) {
        val value = cmp.compare(el1L1!!.value, el1L2!!.value) //ordenadas de modo crescente segundo o comparador cmp
        when {
            value < 0 -> el1L1 = el1L1.next
            value > 0 -> el1L2 = el1L2.next
            else -> {
                val valueCommon = el1L1.value


                var i = el1L1
                while (i !== list1 && cmp.compare(i!!.value, valueCommon) == 0){
                    val next =i!!.next
                    remove(i)
                    if (head == null || cmp.compare(valueCommon, lastValue) != 0) {
                        if (head == null) {
                            head = i
                        } else {
                            tail!!.next = i
                            i.previous = tail
                        }
                        tail = i
                        lastValue =valueCommon
                    }
                    i = next
                }
                el1L1 = i


                while (el1L2 !== list2 && cmp.compare(el1L2!!.value, valueCommon) == 0) {
                    val next = el1L2.next
                    remove(el1L2)
                    el1L2 = next
                }
            }
        }
    }
    return head
}
