@Override
    public LazyPOrderedSetX<T> onEmptySwitch(Supplier<? extends POrderedSet<T>> supplier) {
        return stream(Fluxes.onEmptySwitch(flux(), ()->Flux.fromIterable(supplier.get())));
       
    }