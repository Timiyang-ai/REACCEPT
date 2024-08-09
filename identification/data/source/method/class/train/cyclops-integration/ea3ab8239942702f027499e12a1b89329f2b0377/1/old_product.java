@Override
    public LazyPSetX<T> onEmptySwitch(Supplier<? extends PSet<T>> supplier) {
        return stream(FluxUtils.onEmptySwitch(flux(), ()->Flux.fromIterable(supplier.get())));
       
    }