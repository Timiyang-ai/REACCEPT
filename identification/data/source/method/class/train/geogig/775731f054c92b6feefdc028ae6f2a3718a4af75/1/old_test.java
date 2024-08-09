    private Bucket create(ObjectId id, Envelope bounds) {
        return RevObjectFactory.defaultInstance().createBucket(id, 0, bounds);
    }