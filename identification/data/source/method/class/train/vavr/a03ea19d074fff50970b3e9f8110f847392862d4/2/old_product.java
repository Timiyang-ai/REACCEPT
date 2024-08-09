static BitSet tabulate(int n, Function<Integer, Integer> f) {
        Objects.requireNonNull(f, "f is null");
        return Collections.tabulate(n, f, BitSet.empty(), BitSet::of);
    }