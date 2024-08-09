public static MutableSparseVector userRatingVector(@Nonnull Collection<? extends Rating> ratings) {
        return extractVector(ratings, IdExtractor.ITEM);
    }