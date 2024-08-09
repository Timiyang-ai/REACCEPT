public static Bucket create(final ObjectId bucketTree, final @Nullable Envelope bounds) {
        Preconditions.checkNotNull(bucketTree);
        Float32Bounds b32 = Float32Bounds.valueOf(bounds);
        return new BucketImpl(bucketTree, b32);
    }