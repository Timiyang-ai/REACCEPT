static <T> List<T> fill(Integer n, Supplier<? extends T> s) {
        return List.tabulate(n, anything -> s.get());
    }