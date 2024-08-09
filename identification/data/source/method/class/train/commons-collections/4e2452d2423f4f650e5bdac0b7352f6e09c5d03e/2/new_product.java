public static Set predicatedSet(Set set, Predicate predicate) {
        return PredicatedSet.decorate(set, predicate);
    }