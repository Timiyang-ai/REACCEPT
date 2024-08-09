public static int hashToIndex(int hash, int length) {
        checkPositive(length, "length must be larger than 0");

        if (hash == Integer.MIN_VALUE) {
            return 0;
        }

        return abs(hash) % length;
    }