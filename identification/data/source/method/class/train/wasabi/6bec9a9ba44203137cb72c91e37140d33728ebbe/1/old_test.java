    @Test
    public void doPageAssignmentsTest() throws IOException {
        //Input
        Application.Name appName = Application.Name.valueOf("Test");
        Page.Name pageName = Page.Name.valueOf("TestPage1");
        User.ID user = User.ID.valueOf("testUser");
        Context context = Context.valueOf("TEST");
        SegmentationProfile segmentationProfile = mock(SegmentationProfile.class);
        HttpHeaders headers = mock(HttpHeaders.class);
        Calendar date1 = Calendar.getInstance();
        date1.add(Calendar.DAY_OF_MONTH, -1);
        Calendar date2 = Calendar.getInstance();
        date2.add(Calendar.DAY_OF_MONTH, 10);

        Experiment exp1 = Experiment.withID(Experiment.ID.newInstance())
                .withApplicationName(appName)
                .withLabel(Experiment.Label.valueOf("exp1Label"))
                .withStartTime(date1.getTime())
                .withEndTime(date2.getTime())
                .withSamplingPercent(1.0)
                .withState(Experiment.State.RUNNING)
                .withIsPersonalizationEnabled(false)
                .build();

        Experiment exp2 = Experiment.withID(Experiment.ID.newInstance())
                .withApplicationName(appName)
                .withLabel(Experiment.Label.valueOf("exp2Label"))
                .withStartTime(date1.getTime())
                .withEndTime(date2.getTime())
                .withSamplingPercent(1.0)
                .withState(Experiment.State.RUNNING)
                .withIsPersonalizationEnabled(false)
                .build();

        List<PageExperiment> pageExperiments = newArrayList();
        pageExperiments.add(PageExperiment.withAttributes(exp1.getID(), exp1.getLabel(), true).build());
        pageExperiments.add(PageExperiment.withAttributes(exp2.getID(), exp2.getLabel(), true).build());

        ExperimentBatch experimentBatch = ExperimentBatch.newInstance().withLabels(newHashSet(exp1.getLabel(), exp2.getLabel())).build();
        List<Experiment> expList = newArrayList(exp1, exp2);

        Map expMap = newHashMap();
        expMap.put(exp1.getID(), exp1);
        expMap.put(exp2.getID(), exp2);

        PrioritizedExperimentList pExpList = new PrioritizedExperimentList();
        pExpList.addPrioritizedExperiment(PrioritizedExperiment.from(exp1, 1).build());
        pExpList.addPrioritizedExperiment(PrioritizedExperiment.from(exp2, 2).build());
        Optional<PrioritizedExperimentList> prioritizedExperimentListOptional = Optional.of(pExpList);

        BucketList bucketList1 = new BucketList();
        bucketList1.addBucket(Bucket.newInstance(exp1.getID(), Bucket.Label.valueOf("red")).withAllocationPercent(0.5).build());
        bucketList1.addBucket(Bucket.newInstance(exp1.getID(), Bucket.Label.valueOf("blue")).withAllocationPercent(0.5).build());
        BucketList bucketList2 = new BucketList();
        bucketList2.addBucket(Bucket.newInstance(exp2.getID(), Bucket.Label.valueOf("yellow")).withAllocationPercent(1.0).build());

        List<Experiment.ID> exclusionList = newArrayList();

        //Mock dependent interactions
        when(metadataCache.getPageExperiments(appName, pageName)).thenReturn(pageExperiments);
        when(metadataCache.getExperimentById(exp1.getID())).thenReturn(Optional.of(exp1));
        when(metadataCache.getExperimentById(exp2.getID())).thenReturn(Optional.of(exp2));
        when(metadataCache.getExperimentsByAppName(appName)).thenReturn(expList);
        when(metadataCache.getPrioritizedExperimentListMap(appName)).thenReturn(prioritizedExperimentListOptional);
        when(metadataCache.getBucketList(exp1.getID())).thenReturn(bucketList1);
        when(metadataCache.getBucketList(exp2.getID())).thenReturn(bucketList2);
        when(metadataCache.getExclusionList(exp1.getID())).thenReturn(exclusionList);
        when(metadataCache.getExclusionList(exp2.getID())).thenReturn(exclusionList);

        List<Pair<Experiment, String>> existingAssignments = newArrayList(new ImmutablePair<>(exp2, "yellow"));
        Mockito.when(assignmentsRepository.getAssignments(user, appName, context, expMap)).thenReturn(existingAssignments);

        //This is real call to the method
        List<Assignment> resultAssignments = assignmentsImpl.doPageAssignments(appName, pageName, user,
                context, true, false, headers, segmentationProfile,false);

        //Verify result
        assertThat(resultAssignments.size(), is(2));
        assertThat(resultAssignments.get(0).getBucketLabel().toString(), anyOf(is("red"), is("blue")));
        assertThat(resultAssignments.get(0).getStatus().toString(), is(Assignment.Status.NEW_ASSIGNMENT.toString()));
        assertThat(resultAssignments.get(1).getBucketLabel().toString(), is("yellow"));
        assertThat(resultAssignments.get(1).getStatus().toString(), is(Assignment.Status.EXISTING_ASSIGNMENT.toString()));
    }