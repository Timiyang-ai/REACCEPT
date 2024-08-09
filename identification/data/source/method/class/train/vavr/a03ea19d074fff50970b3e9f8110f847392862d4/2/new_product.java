static BitSet<Integer> tabulate(int n, Function<Integer, Integer> f) {
        Objects.requireNonNull(f, "f is null");
        return empty().addAll(Collections.tabulate(n, f));
    }