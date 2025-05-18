package serie2.part4


 class AEDHashMAP<K, V> (initialCapacity: Int = 16, val loadFactor: Float = 0.75f) : AEDMutableMap<K, V> {
    private class HashNode<K, V>(override val key: K, override var value: V,
                                 var next: HashNode<K, V>? = null
    ): MutableMap.MutableEntry<K,V>, AEDMutableMap.MutableEntry<K, V> {
        var hc = key.hashCode()
        override fun setValue(newValue: V): V {
            val oldValue = value
            value = newValue
            return oldValue
        }
    }

    override var capacity = initialCapacity
    private var table: Array<HashNode<K, V>?> = arrayOfNulls(capacity)
    override var size = 0


    private fun hash(key: K): Int {
        var idx = key.hashCode() % capacity
        if (idx < 0) idx += capacity
        return idx
    }

    override operator fun get(key: K): V? {
        val i = hash(key)
        var curr = table[i]
        while (curr != null) {
            if (curr.key == key) return curr.value
            curr = curr.next
        }
        return null
    }

    override fun put(key: K, value: V): V? {
        if (size.toDouble() * loadFactor >= capacity) expand()
        val idx = hash(key)
        var curr = table[idx]

        while (curr != null) {
            if (curr.key == key) {
                val old = curr.value
                curr.value = value
                return old
            }
            curr = curr.next
        }



        val node = HashNode(key, value, next = table[idx])
        node.next = table[idx]
        table[idx] = node
        size = size + 1
        return null
    }

    private fun expand() {
        //Aumentar a dimensão da tabela e recalcular a posição dos elementos na nova tabela
        capacity *= 2
        val newTable = arrayOfNulls<HashNode<K, V>?>(capacity)
        for (i in table.indices) {
            var curr = table[i]
            while (curr != null) {
                val next = curr.next
                val idx = hash(curr.key) % capacity
                curr.next = newTable[idx]
                newTable[idx] = curr
                curr = next
            }
        }
        table = newTable
    }

    private inner class MyIterator: Iterator<AEDMutableMap.MutableEntry<K,V>>{
            var currIdx = -1
            var currNode: HashNode<K, V>? = null
            var list: HashNode<K, V>? = null

            override fun hasNext(): Boolean {
                if (currNode != null) return true
                while (capacity > currIdx) {
                    if (list == null) {
                        currIdx++
                        if (currIdx < capacity) list = table[currIdx]
                    } else {
                        currNode = list
                        list = list!!.next
                        return true
                    }
                }
                return false
            }

            override fun next(): AEDMutableMap.MutableEntry<K,V> {
                if (!hasNext()) throw NoSuchElementException() // faz uma quebra do programa ( ex: quando for um indice invalido)
                val aux = currNode!!
                currNode = null
                return aux
            }

    }
    override fun iterator(): Iterator<AEDMutableMap.MutableEntry<K,V>> = MyIterator()
}

