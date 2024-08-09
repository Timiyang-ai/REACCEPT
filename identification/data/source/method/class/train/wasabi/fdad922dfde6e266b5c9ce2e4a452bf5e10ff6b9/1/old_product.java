Assignment createAssignmentObject(Experiment experiment, User.ID userID, Context context, boolean selectBucket,
                                      BucketList bucketList, Date date, SegmentationProfile segmentationProfile) {

        Assignment.Builder builder = Assignment.newInstance(experiment.getID())
                .withExperimentLabel(getExperimentLabel(experiment.getID()))
                .withApplicationName(experiment.getApplicationName())
                .withUserID(userID)
                .withContext(context);

        if (selectBucket) {
            Bucket assignedBucket;
            /*
            With the bucket state in effect, this part of the code could also result in
            the generation of a null assignment in cases where the selected bucket is CLOSED or EMPTY
            In CLOSED state, the current bucket assignments to the bucket are left as is and all the
            assignments to this bucket are made null going forward.

            In EMPTY state, the current bucket assignments are made null as well as all the future assignments
            to this bucket are made null.

            Retrieves buckets from Repository if personalization is not enabled and if skipBucketRetrieval is false
            Retrieves buckets from Assignment Decorator if personalization is enabled
            */
            BucketList bucketsExternal = getBucketList(experiment, userID, segmentationProfile,
                    !Objects.isNull(bucketList) /* do not skip retrieving bucket from repository if bucketList is null */);
            if (Objects.isNull(bucketsExternal) || bucketsExternal.getBuckets().isEmpty()) {
                // if bucketlist obtained from Assignment Decorator is null; use the bucketlist passed into the method
                assignedBucket = selectBucket(bucketList.getBuckets());
            } else {
                //else use the bucketExternal obtained from the Assignment Decorator
                assignedBucket = selectBucket(bucketsExternal.getBuckets());
            }
            //check that at least one bucket was open
            if (!Objects.isNull(assignedBucket)) {
                //create the bucket with bucketlabel
                builder.withBucketLabel(assignedBucket.getLabel());
                builder.withPayload(getBucketPayload(experiment.getID(), nonNull(assignedBucket.getLabel())?assignedBucket.getLabel().toString():null));
            } else {
                return nullAssignment(userID, experiment.getApplicationName(), experiment.getID(),
                        Assignment.Status.NO_OPEN_BUCKETS);
            }

        } else {
            builder.withBucketLabel(null);
        }

        Assignment result = builder
                .withStatus(Assignment.Status.NEW_ASSIGNMENT)
                .withCreated(Optional.ofNullable(date).orElseGet(Date::new))
                .withCacheable(false)
                .build();
        LOGGER.debug("result => {}", result);
        return result;
    }