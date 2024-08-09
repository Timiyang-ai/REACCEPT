public static <T> ConnectableFlowable<T> observeOn(final ConnectableFlowable<T> cf, final Scheduler scheduler) {
        final Flowable<T> flowable = cf.observeOn(scheduler);
        return RxJavaPlugins.onAssembly(new ConnectableFlowableReplay<T>(cf, flowable));
    }