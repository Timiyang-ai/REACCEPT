public static Collection predicatedCollection(Collection collection, Predicate predicate) {
        return PredicatedCollection.decorate(collection, predicate);
    }