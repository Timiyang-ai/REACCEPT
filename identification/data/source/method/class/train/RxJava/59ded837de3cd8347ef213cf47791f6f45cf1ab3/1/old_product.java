public static <R> Observable<R> fromFunc0(Func0<? extends R> function, Scheduler scheduler) {
        return Observable.create(OperationFromFunctionals.fromFunc0(function)).subscribeOn(scheduler);
    }