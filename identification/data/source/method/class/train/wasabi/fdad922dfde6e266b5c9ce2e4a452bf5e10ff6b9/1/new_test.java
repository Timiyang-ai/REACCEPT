    @Test
    public void createAssignmentObjectTest() throws IOException {

        //------- Prepare input data
        Date date = new Date();
        SegmentationProfile segmentationProfile = null;
        User.ID userID = User.ID.valueOf("test-user-1");
        Context context = Context.valueOf("TEST");
        boolean selectBucket = true;
        Experiment exp1 = Experiment.withID(Experiment.ID.valueOf(UUID.randomUUID()))
                .withApplicationName(Application.Name.valueOf("test-app-1"))
                .withLabel(Experiment.Label.valueOf("test-exp-1"))
                .withState(Experiment.State.RUNNING)
                .withIsPersonalizationEnabled(false)
                .build();
        BucketList bucketList1 = new BucketList();
        bucketList1.addBucket(Bucket.newInstance(exp1.getID(), Bucket.Label.valueOf("bucket-1")).withAllocationPercent(0.9d).withPayload("bucket1").withState(Bucket.State.OPEN).build());
        bucketList1.addBucket(Bucket.newInstance(exp1.getID(), Bucket.Label.valueOf("bucket-2")).withAllocationPercent(0.1d).withPayload("bucket-2").withState(Bucket.State.OPEN).build());

        //--------- Mock calls
        when(assignmentDecorator.getBucketList(exp1, userID, segmentationProfile)).thenReturn(bucketList1);
        when(metadataCache.getExperimentById(exp1.getID())).thenReturn(Optional.of(exp1));

        //--------- Make actual call
        Assignment newAssignment = assignmentsImpl.createAssignmentObject(exp1, userID, context, selectBucket, bucketList1, date, segmentationProfile);

        //---------- Validate result
        assertTrue(newAssignment != null);
        assertTrue(newAssignment.getStatus().equals(Assignment.Status.NEW_ASSIGNMENT));

    }