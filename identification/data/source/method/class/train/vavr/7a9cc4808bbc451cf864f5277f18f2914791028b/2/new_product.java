public static <T extends Comparable<? super T>> Collector<T, ArrayList<T>, TreeSet<T>> collector() {
        return collector(naturalComparator());
    }