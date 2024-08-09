@Override
    public LazyPOrderedSetX<T> onEmptySwitch(Supplier<? extends POrderedSet<T>> supplier) {
        return stream(FluxUtils.onEmptySwitch(flux(), ()->Flux.fromIterable(supplier.get())));
       
    }