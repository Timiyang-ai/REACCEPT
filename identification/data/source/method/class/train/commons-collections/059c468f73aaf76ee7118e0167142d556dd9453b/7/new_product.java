public static <E> SetView<E> disjunction(final Set<? extends E> setA, final Set<? extends E> setB) {
        Objects.requireNonNull(setA, "setA");
        Objects.requireNonNull(setB, "setB");
        
        final SetView<E> aMinusB = difference(setA, setB);
        final SetView<E> bMinusA = difference(setB, setA);

        return new SetView<E>() {
            @Override
            public boolean contains(final Object o) {
                return setA.contains(o) ^ setB.contains(o);
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