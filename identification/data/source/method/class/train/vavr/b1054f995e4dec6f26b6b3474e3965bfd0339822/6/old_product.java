public <K extends Comparable<? super K>, V2 extends V> TreeMultimap<K, V2> of(K key, V2 value) {
            return of((Comparator<? super K> & Serializable) K::compareTo, key, value);
        }