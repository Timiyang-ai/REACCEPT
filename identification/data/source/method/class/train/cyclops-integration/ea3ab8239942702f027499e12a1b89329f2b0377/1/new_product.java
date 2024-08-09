@Override
    public LazyPSetX<T> onEmptySwitch(Supplier<? extends PSet<T>> supplier) {
        return stream(Fluxes.onEmptySwitch(flux(), ()->Flux.fromIterable(supplier.get())));
       
    }