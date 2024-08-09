public static boolean isExactlyOneTrue(final boolean... array) {
        // Validates input
        if (array == null) {
            throw new IllegalArgumentException("The Array must not be null");
        }
        if (array.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }

        // Loops through array, comparing each item
        int trueCount = 0;
        for (final boolean element : array) {
            // If item is true, and trueCount is < 1, increments count
            // Else, isExactlyOneTrue fails
            if (element) {
                if (trueCount < 1) {
                    trueCount++;
                } else {
                    return false;
                }
            }
        }

        // Returns true if there was exactly 1 true item
        return trueCount == 1;
    }