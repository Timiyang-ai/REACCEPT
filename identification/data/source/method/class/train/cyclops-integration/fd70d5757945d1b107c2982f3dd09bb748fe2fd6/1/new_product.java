@Override
    public LazyPStackX<T> onEmptySwitch(Supplier<? extends PStack<T>> supplier) {
        return stream(Fluxes.onEmptySwitch(flux(), ()->Flux.fromIterable(supplier.get())));
       
    }