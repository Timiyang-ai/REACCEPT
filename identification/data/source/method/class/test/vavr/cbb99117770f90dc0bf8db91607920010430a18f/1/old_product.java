public static <T extends Comparable<T>> PriorityQueue<T> empty() {
        return empty(naturalComparator());
    }