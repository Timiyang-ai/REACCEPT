static <T> Tree<T> fill(int n, Supplier<? extends T> s) {
        Objects.requireNonNull(s, "s is null");
        return Collections.fill(n, s, Tree.empty(), Tree::of);
    }