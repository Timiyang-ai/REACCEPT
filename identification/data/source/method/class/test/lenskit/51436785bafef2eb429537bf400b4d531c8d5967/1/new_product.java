public static Long2DoubleMap userRatingVector(@Nonnull Collection<? extends Rating> ratings) {
        return extractVector(ratings, IdExtractor.ITEM);
    }