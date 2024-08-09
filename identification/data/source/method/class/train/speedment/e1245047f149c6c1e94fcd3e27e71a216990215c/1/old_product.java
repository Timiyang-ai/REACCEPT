@Override
    public MapStream<K, V> sorted() {
        final Comparator<K> c = (a, b) -> {
            if (a == null && b == null) {
                return 0;
            } else if (a != null && b != null) {
                if (a instanceof Comparable<?>) {
                    @SuppressWarnings("unchecked")
                    final Comparable<K> ac = (Comparable<K>) a;

                    return ac.compareTo(b);
                }
            }

            throw new UnsupportedOperationException("Can only sort keys that implement Comparable.");
        };

        inner = inner.sorted((a, b) -> c.compare(a.getKey(), b.getKey()));
        return this;
    }