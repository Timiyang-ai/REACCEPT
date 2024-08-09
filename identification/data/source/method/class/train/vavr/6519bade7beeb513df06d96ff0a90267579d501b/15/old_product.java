static <T> List<T> fill(int n, Supplier<? extends T> s) {
        return List.tabulate(n, anything -> s.get());
    }