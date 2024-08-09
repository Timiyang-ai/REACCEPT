public static <T> Set<T> predicatedSet(Set<T> set, Predicate<? super T> predicate) {
        return PredicatedSet.predicatedSet(set, predicate);
    }