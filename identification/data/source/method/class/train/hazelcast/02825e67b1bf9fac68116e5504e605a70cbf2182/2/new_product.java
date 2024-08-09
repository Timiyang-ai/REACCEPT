public static int hashToIndex(int hash, int length) {
        checkPositive(length, "length must be larger than 0");

        if (hash == Integer.MIN_VALUE) {
            hash = 0;
        } else {
            hash = abs(hash);
        }

        return hash % length;
    }