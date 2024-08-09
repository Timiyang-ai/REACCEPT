public static <E> SetView<E> union(final Set<? extends E> setA, final Set<? extends E> setB) {
        Objects.requireNonNull(setA, "setA");
        Objects.requireNonNull(setB, "setB");

        final SetView<E> bMinusA = difference(setB, setA);

        return new SetView<E>() {
            @Override
            public boolean contains(final Object o) {
                return setA.contains(o) || setB.contains(o);
            }

            @Override
            public Iterator<E> createIterator() {
                return IteratorUtils.chainedIterator(setA.iterator(), bMinusA.iterator());
            }

            @Override
            public boolean isEmpty() {
                return setA.isEmpty() && setB.isEmpty();
            }

            @Override
            public int size() {
                return setA.size() + bMinusA.size();
            }
        };
    }