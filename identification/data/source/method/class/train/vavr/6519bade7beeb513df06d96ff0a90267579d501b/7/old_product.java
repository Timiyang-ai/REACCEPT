static <T> List<T> fill(Integer n, Supplier<? extends T> s) {
        return List.fill(n, anything -> s.get());
    }