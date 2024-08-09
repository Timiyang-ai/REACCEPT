public static <T> int count(Iterable<T> iterable, Predicate<? super T> predicate, int batchSize, Executor executor)
    {
        CountCombiner<T> combiner = new CountCombiner<>();
        CountProcedureFactory<T> procedureFactory = new CountProcedureFactory<>(predicate);
        ParallelIterate.forEach(
                iterable,
                procedureFactory,
                combiner,
                batchSize,
                executor);
        return combiner.getCount();
    }