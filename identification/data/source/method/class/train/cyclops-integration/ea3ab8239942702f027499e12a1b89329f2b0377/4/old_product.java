@Override
    public LazyPVectorX<T> onEmptySwitch(Supplier<? extends PVector<T>> supplier) {
        return stream(FluxUtils.onEmptySwitch(flux(), ()->Flux.fromIterable(supplier.get())));
       
    }