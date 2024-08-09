static BitSet<Integer> fill(int n, Supplier<Integer> s) {
        Objects.requireNonNull(s, "s is null");
        return empty().addAll(Collections.fill(n, s));
    }