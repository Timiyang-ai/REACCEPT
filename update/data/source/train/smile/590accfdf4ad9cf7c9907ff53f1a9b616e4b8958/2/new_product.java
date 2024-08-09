public static double lfactorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException(String.format("n has to be non-negative: %d", n));
        }

        double f = 0.0;
        for (int i = 2; i <= n; i++) {
            f += log(i);
        }

        return f;
    }