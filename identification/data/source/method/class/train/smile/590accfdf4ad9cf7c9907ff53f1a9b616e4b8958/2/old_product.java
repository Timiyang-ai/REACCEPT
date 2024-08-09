public static double logChoose(int n, int k) {
        if (n < 0 || k < 0 || k > n) {
            throw new IllegalArgumentException(String.format("Invalid n = %d, k = %d", n, k));
        }

        return logFactorial(n) - logFactorial(k) - logFactorial(n - k);
    }