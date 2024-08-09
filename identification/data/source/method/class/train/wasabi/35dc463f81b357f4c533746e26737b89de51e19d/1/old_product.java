@Override
    public Map<Bucket.Label, BucketCounts> getEmptyBuckets(Experiment.ID experimentID)
            throws RepositoryException {

        try {
            List<Map> bucketRows = transaction.select("select label from bucket where experiment_id=?", experimentID);

            Counts impressions = new Counts.Builder().withEventCount(0).withUniqueUserCount(0).build();
            Counts jointActions = new Counts.Builder().withEventCount(0).withUniqueUserCount(0).build();
            Map<Event.Name, ActionCounts> perDayBucketActions = new HashMap<>();
            BucketCounts blankBucket = new BucketCounts.Builder().withImpressionCounts(impressions)
                    .withJointActionCounts(jointActions)
                    .withActionCounts(perDayBucketActions).build();
            Map<Bucket.Label, BucketCounts> buckets = new HashMap<>();

            for (Map bucketRow : bucketRows) {
                Bucket.Label externalLabel =
                        Bucket.Label.valueOf((String) bucketRow.get("label"));
                BucketCounts bucket = blankBucket.clone();

                bucket.setLabel(externalLabel);
                buckets.put(externalLabel, bucket);
            }
            return buckets;

        } catch (Exception e) {
            throw new RepositoryException("error reading actions rows from MySQL", e);
        }
    }