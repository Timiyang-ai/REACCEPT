public static <E> SetView<E> intersection(final Set<? extends E> a, final Set<? extends E> b) {
        if (a == null || b == null) {
            throw new NullPointerException("Sets must not be null.");
        }

        final Predicate<E> containedInB = object -> b.contains(object);

        return new SetView<E>() {
            @Override
            public boolean contains(final Object o) {
                return a.contains(o) && b.contains(o);
            }

            @Override
            public Iterator<E> createIterator() {
                return IteratorUtils.filteredIterator(a.iterator(), containedInB);
            }
        };
    }