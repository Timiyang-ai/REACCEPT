public static <K extends Comparable<? super K>, V> Collector<Tuple2<K, V>, ArrayList<Tuple2<K, V>>, TreeMap<K, V>> collector() {
        return createCollector(EntryComparator.natural());
    }