public <K extends Comparable<? super K>, V2 extends V> TreeMultimap<K, V2> of(Tuple2<? extends K, ? extends V2> entry) {
            return of(naturalComparator(), entry);
        }