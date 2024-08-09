    public static <T> StreamEx<T> generate(Supplier<T> supplier) {
        return StreamEx.produce(action -> {
            action.accept(supplier.get());
            return true;
        });
    }