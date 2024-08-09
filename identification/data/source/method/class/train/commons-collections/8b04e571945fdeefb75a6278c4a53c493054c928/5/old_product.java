@SuppressWarnings("unchecked")
    public FluentIterable<E> zip(final E... elements) {
        return zip(Arrays.asList(elements));
    }