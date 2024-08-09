@Test
    public void testGetJobStatus() throws GenieNotFoundException {
        final String id = UUID.randomUUID().toString();
        final JobStatus status = JobStatus.RUNNING;

        Mockito
            .when(this.jobRepository.findByUniqueId(id, JobStatusProjection.class))
            .thenReturn(Optional.empty())
            .thenReturn(Optional.of(() -> status));
        try {
            this.jobPersistenceService.getJobStatus(id);
            Assert.fail("Should have thrown GenieNotFoundException");
        } catch (final GenieNotFoundException e) {
            // expected
        }
        Assert.assertThat(this.jobPersistenceService.getJobStatus(id), Matchers.is(status));
    }