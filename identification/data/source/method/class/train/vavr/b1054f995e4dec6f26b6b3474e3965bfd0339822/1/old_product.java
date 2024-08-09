@SuppressWarnings("unchecked")
        public final <K extends Comparable<? super K>, V2 extends V> TreeMultimap<K, V2> of(Object... pairs) {
            return of((Comparator<? super K> & Serializable) K::compareTo, pairs);
        }