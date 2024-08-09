public static <T> int count(Iterable<T> iterable, Predicate<? super T> predicate, int batchSize, Executor executor)
    {
        CountCombiner<T> combiner = new CountCombiner<T>();
        CountProcedureFactory<T> procedureFactory = new CountProcedureFactory<T>(predicate);
        ParallelIterate.forEach(
                iterable,
                procedureFactory,
                combiner,
                batchSize,
                executor);
        return combiner.getCount();
    }