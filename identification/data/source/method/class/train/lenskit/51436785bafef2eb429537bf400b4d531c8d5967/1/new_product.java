public static Long2DoubleMap itemRatingVector(@Nonnull Collection<? extends Rating> ratings) {
        return extractVector(ratings, IdExtractor.USER);
    }