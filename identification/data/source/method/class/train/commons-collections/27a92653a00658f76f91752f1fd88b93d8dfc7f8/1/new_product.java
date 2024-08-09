public static <O> List<List<O>> partition(final Iterable<? extends O> iterable,
                                              final Predicate<? super O>... predicates) {

        @SuppressWarnings({ "unchecked", "rawtypes" }) // safe
        final Factory<List<O>> factory = FactoryUtils.instantiateFactory((Class) ArrayList.class);
        return partition(iterable, factory, predicates);
    }