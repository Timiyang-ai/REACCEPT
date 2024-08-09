public static <K extends Comparable<? super K>, V> TreeMap<K, V> empty() {
        return new TreeMap<>(RedBlackTree.empty(EntryComparator.natural()));
    }