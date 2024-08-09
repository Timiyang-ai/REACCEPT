@Override
    public LazyPQueueX<T> onEmptySwitch(Supplier<? extends PQueue<T>> supplier) {
        return stream(Fluxes.onEmptySwitch(flux(), ()->Flux.fromIterable(supplier.get())));
       
    }