@Override
    public LazyPStackX<T> onEmptySwitch(Supplier<? extends PStack<T>> supplier) {
        return stream(FluxUtils.onEmptySwitch(flux(), ()->Flux.fromIterable(supplier.get())));
       
    }