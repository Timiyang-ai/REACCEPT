static BitSet<Integer> tabulate(int n, Function<Integer, Integer> f) {
        return Builder.DEFAULT.tabulate(n, f);
    }