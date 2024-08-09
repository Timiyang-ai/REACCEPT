public BitSet<T> fill(int n, Supplier<? extends T> s) {
            Objects.requireNonNull(s, "s is null");
            return empty().addAll(Collections.fill(n, s));
        }