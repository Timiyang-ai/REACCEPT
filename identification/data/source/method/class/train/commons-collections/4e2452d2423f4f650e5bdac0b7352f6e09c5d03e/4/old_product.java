public static Set predicatedSet(Set set, Predicate p) {
        return new PredicatedSet(set, p);
    }