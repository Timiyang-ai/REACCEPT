static BitSet fill(int n, Supplier<Integer> s) {
        Objects.requireNonNull(s, "s is null");
        return Collections.fill(n, s, BitSet.empty(), BitSet::of);
    }