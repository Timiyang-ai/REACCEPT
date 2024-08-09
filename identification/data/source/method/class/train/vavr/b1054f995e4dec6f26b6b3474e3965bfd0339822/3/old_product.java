public <K extends Comparable<? super K>, V2 extends V> TreeMultimap<K, V2> of(K k1, V2 v1, K k2, V2 v2, K k3, V2 v3, K k4, V2 v4, K k5, V2 v5, K k6, V2 v6, K k7, V2 v7) {
            return of((Comparator<? super K> & Serializable) K::compareTo, k1, v1, k2, v2, k3, v3, k4, v4, k5, v5, k6, v6, k7, v7);
        }