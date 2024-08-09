public static float nextFloat(float startInclusive, float endInclusive) {
        Validate.isTrue(endInclusive >= startInclusive,
                "Start value must be smaller or equal to end value.");
        Validate.isTrue(startInclusive >= 0, "Both range values must be non-negative.");
        
        if (startInclusive == endInclusive) {
            return startInclusive;
        }
        
        return startInclusive + ((endInclusive - startInclusive) * RANDOM.nextFloat());
    }