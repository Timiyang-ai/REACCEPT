public static void throwIfIllegalArrayRange(long startIndex, int length, long lowBoundInclusive, long upBoundExclusive, String startIndexArgName, String lengthArgName) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        // Check for non-negative length.
        if (length < 0) {
            throw new IllegalArgumentException(String.format("%s must be a non-negative integer.", lengthArgName));
        }

        // Check for valid start index.
        if (startIndex < lowBoundInclusive || startIndex >= upBoundExclusive) {
            throw new ArrayIndexOutOfBoundsException(String.format("%s: value must be in interval [%d, %d), given %d.", startIndexArgName, lowBoundInclusive, upBoundExclusive, startIndex));
        }

        // Check for valid end offset. Note that end offset can be equal to upBoundExclusive, because this is a range.
        if (startIndex + length > upBoundExclusive) {
            throw new ArrayIndexOutOfBoundsException(String.format("%s + %s: value must be in interval [%d, %d], actual %d.", startIndexArgName, lengthArgName, lowBoundInclusive, upBoundExclusive, startIndex + length));
        }
    }