public static Set predicatedSet(Set set, Predicate predicate) {
        return new PredicatedSet(set, predicate);
    }