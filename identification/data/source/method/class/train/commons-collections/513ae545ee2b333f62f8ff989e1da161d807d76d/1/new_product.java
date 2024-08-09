public static <O> List<List<O>> partition(final Iterable<? extends O> iterable,
                                              final Predicate<? super O> predicate) {
        Objects.requireNonNull(predicate, "predicate");
        @SuppressWarnings({ "unchecked", "rawtypes" }) // safe
        final Factory<List<O>> factory = FactoryUtils.instantiateFactory((Class) ArrayList.class);
        @SuppressWarnings("unchecked") // safe
        final Predicate<? super O>[] predicates = new Predicate[] { predicate };
        return partition(iterable, factory, predicates);
    }