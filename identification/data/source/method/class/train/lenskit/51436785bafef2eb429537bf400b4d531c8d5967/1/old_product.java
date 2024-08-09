public static MutableSparseVector itemRatingVector(@Nonnull Collection<? extends Rating> ratings) {
        return extractVector(ratings, IdExtractor.USER);
    }