@Override
    public LazyPStackX<T> onEmptySwitch(Supplier<? extends List<T>> supplier) {
        return stream(FluxUtils.onEmptySwitch(flux(), ()->Flux.fromIterable(supplier.get())));
       
    }