public DoubleStream mapToDouble(ToDoubleBiFunction<? super K, ? super V> mapper) {
        return inner.mapToDouble(e -> mapper.applyAsDouble(e.getKey(), e.getValue()));
    }