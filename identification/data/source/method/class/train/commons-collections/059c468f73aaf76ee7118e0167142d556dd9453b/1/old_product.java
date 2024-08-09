public static <E> SetView<E> union(final Set<? extends E> a, final Set<? extends E> b) {
        if (a == null || b == null) {
            throw new NullPointerException("Sets must not be null.");
        }

        final SetView<E> bMinusA = difference(b, a);

        return new SetView<E>() {
            @Override
            public boolean contains(final Object o) {
                return a.contains(o) || b.contains(o);
            }

            @Override
            public Iterator<E> createIterator() {
                return IteratorUtils.chainedIterator(a.iterator(), bMinusA.iterator());
            }

            @Override
            public boolean isEmpty() {
                return a.isEmpty() && b.isEmpty();
            }

            @Override
            public int size() {
                return a.size() + bMinusA.size();
            }
        };
    }