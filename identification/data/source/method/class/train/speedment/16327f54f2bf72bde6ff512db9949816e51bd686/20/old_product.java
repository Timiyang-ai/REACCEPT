public DoubleStream flatMapToDouble(BiFunction<? super K, ? super V, ? extends DoubleStream> mapper) {
        return inner.flatMapToDouble(e -> mapper.apply(e.getKey(), e.getValue()));
    }