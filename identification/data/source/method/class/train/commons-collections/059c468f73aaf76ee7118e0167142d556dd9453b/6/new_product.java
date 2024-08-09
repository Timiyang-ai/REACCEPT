public static <E> SetView<E> difference(final Set<? extends E> setA, final Set<? extends E> setB) {
        Objects.requireNonNull(setA, "setA");
        Objects.requireNonNull(setB, "setB");

        final Predicate<E> notContainedInB = object -> !setB.contains(object);

        return new SetView<E>() {
            @Override
            public boolean contains(final Object o) {
                return setA.contains(o) && !setB.contains(o);
            }

            @Override
            public Iterator<E> createIterator() {
                return IteratorUtils.filteredIterator(setA.iterator(), notContainedInB);
            }
        };
    }