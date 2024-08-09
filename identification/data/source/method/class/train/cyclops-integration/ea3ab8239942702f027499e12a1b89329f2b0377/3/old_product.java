@Override
    public LazyPQueueX<T> onEmptySwitch(Supplier<? extends PQueue<T>> supplier) {
        return stream(FluxUtils.onEmptySwitch(flux(), ()->Flux.fromIterable(supplier.get())));
       
    }