public static <T> ConnectableFlowable<T> observeOn(final ConnectableFlowable<T> cf, final Scheduler scheduler) {
        final Flowable<T> observable = cf.observeOn(scheduler);
        return RxJavaPlugins.onAssembly(new ConnectableFlowableReplay<T>(cf, observable));
    }