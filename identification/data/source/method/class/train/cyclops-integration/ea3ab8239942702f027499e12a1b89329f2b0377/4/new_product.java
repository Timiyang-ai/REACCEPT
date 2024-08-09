@Override
    public LazyPVectorX<T> onEmptySwitch(Supplier<? extends PVector<T>> supplier) {
        return stream(Fluxes.onEmptySwitch(flux(), ()->Flux.fromIterable(supplier.get())));
       
    }