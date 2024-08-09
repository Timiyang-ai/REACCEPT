public static <E> SetView<E> disjunction(final Set<? extends E> a, final Set<? extends E> b) {
        if (a == null || b == null) {
            throw new NullPointerException("Sets must not be null.");
        }

        final SetView<E> aMinusB = difference(a, b);
        final SetView<E> bMinusA = difference(b, a);

        return new SetView<E>() {
            @Override
            public boolean contains(Object o) {
                return a.contains(o) ^ b.contains(o);
            }

            @Override
            public Iterator<E> createIterator() {
                return IteratorUtils.chainedIterator(aMinusB.iterator(), bMinusA.iterator());
            }

            @Override
            public boolean isEmpty() {
                return aMinusB.isEmpty() && bMinusA.isEmpty();
            }

            @Override
            public int size() {
                return aMinusB.size() + bMinusA.size();
            }
        };
    }