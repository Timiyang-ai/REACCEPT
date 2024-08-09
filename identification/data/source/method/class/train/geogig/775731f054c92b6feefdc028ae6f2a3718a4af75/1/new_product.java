public static Bucket create(final @NonNull ObjectId bucketTree,
            final @Nullable Envelope bounds) {
        return RevObjectFactory.defaultInstance().createBucket(bucketTree, bounds);
    }