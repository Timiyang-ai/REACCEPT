public static <C> Collection<C> predicatedCollection(final Collection<C> collection, final Predicate<? super C> predicate) {
        return PredicatedCollection.predicatedCollection(collection, predicate);
    }