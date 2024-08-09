public <K extends Comparable<? super K>, V2 extends V> TreeMultimap<K, V2> of(K k1, V2 v1, K k2, V2 v2, K k3, V2 v3) {
            return of((Comparator<? super K> & Serializable) K::compareTo, k1, v1, k2, v2, k3, v3);
        }