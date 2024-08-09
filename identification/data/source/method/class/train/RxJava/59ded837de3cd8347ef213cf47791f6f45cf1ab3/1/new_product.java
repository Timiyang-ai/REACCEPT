@Deprecated
    public static <R> Observable<R> fromFunc0(Func0<? extends R> function, Scheduler scheduler) {
        return fromCallable(function, scheduler);
    }