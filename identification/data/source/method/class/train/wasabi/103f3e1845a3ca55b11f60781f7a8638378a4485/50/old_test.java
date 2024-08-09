    @Test
    public void toMapTest() {

        Assignment assignment = Assignment.newInstance(Experiment.ID.newInstance())
                .withExperimentLabel(Experiment.Label.valueOf("TestExpLabel"))
                .withApplicationName(Application.Name.valueOf("testApp"))
                .withUserID(User.ID.valueOf("TestUser"))
                .withBucketLabel(Bucket.Label.valueOf("red"))
                .withPayload("RedBucketPayload")
                .withCacheable(false)
                .withContext(Context.valueOf("TEST"))
                .withStatus(Status.EXISTING_ASSIGNMENT)
                .build();

        Map<String, Object> response1 = resource.toSingleAssignmentResponseMap(assignment);
        assertThat(response1.size(), is(5));
        assertNull(response1.get("experimentLabel"));

        Map<String, Object> response2 = resource.toBatchAssignmentResponseMap(assignment);
        assertThat(response2.size(), is(4));
        assertNotNull(response2.get("experimentLabel"));
    }