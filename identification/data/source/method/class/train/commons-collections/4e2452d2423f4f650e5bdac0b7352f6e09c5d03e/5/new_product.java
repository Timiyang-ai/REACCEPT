public static <T> Set<T> predicatedSet(Set<T> set, Predicate<? super T> predicate) {
        return PredicatedSet.decorate(set, predicate);
    }