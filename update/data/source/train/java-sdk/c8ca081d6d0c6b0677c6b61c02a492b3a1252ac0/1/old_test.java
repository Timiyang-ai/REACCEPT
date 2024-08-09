@Test
    public void testCreateJob() {
        // Expected Mock response
        String batchId = UUID.randomUUID().toString();
        String jobId = UUID.randomUUID().toString();
        String jobName = "testJob";

        CreateJobResponse response = new CreateJobResponse();
        response.setId(jobId);
        response.setName(jobName);
        response.setLinks(Arrays.asList(createLink("self", JOBS_PATH + "/" + jobId)));

        mockServer.when(
                request().withMethod("POST").withPath(JOBS_PATH)
        ).respond((response().withBody(GsonSingleton.getGson().toJson(response))));

        // Call create batch
        CreateJobResponse job = service.createJob(jobName, batchId, ConversionTarget.ANSWER_UNITS);
        Assert.assertNotNull(job);
        Assert.assertEquals(job.toString(), response.toString());
    }