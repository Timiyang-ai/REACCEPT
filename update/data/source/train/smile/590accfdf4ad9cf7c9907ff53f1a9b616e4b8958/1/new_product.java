public static double lchoose(int n, int k) {
        if (n < 0 || k < 0 || k > n) {
            throw new IllegalArgumentException(String.format("Invalid n = %d, k = %d", n, k));
        }

        return lfactorial(n) - lfactorial(k) - lfactorial(n - k);
    }