package serie2.part1_2


fun minimum(maxHeap: Array<Int>, heapSize: Int): Int {

          var min = maxHeap[heapSize / 2]
          val folha = heapSize / 2
          for (i in folha .. heapSize - 1) {
               if (maxHeap[i] < min) min = maxHeap[i]
          }
          return min
}


