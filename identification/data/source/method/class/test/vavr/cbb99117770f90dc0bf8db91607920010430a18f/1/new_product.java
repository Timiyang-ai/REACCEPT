public static <T extends Comparable<? super T>> PriorityQueue<T> empty() {
        return empty(naturalComparator());
    }