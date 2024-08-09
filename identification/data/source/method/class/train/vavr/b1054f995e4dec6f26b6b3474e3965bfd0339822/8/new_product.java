public <K extends Comparable<? super K>, V2 extends V> TreeMultimap<K, V2> of(K k1, V2 v1, K k2, V2 v2) {
            return of(naturalComparator(), k1, v1, k2, v2);
        }